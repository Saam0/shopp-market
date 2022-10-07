package com.shopmarket.services.impl;

import com.shopmarket.models.*;
import com.shopmarket.repositories.BillRepository;
import com.shopmarket.repositories.OrderProductRepository;
import com.shopmarket.repositories.OrderRepository;
import com.shopmarket.services.CartService;
import com.shopmarket.services.OrderService;
import com.shopmarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public Order saveOrder(String userEmail, String ccNumber) {
        User user = userService.findUserByEmail(userEmail).get();
        Cart cart = user.getCart();
        Order order = new Order();
        order.setUser(user);
        order.setOrderProducts(fillCartItemsToOrderProducts(cart, order));
        Date date = new Date();
        order.setDateCreated(date);
        order.setProductsCost(order.getProductsCost());
        order.setBill(saveBill(order, date, ccNumber));
        return orderRepository.save(order);
    }

    private Bill saveBill(Order order, Date date, String ccNumber) {
        Bill bill = new Bill();
        bill.setOrder(order);
        bill.setDateCreated(date);
        bill.setCcNumber(ccNumber);
        bill.setPayed(true);
        bill.setNumber(new Random().nextInt(999999999));
        bill.setTotalCost(order.getProductsCost() + order.getDeliveryCost());
        return billRepository.save(bill);
    }

    private Set<OrderProduct> fillCartItemsToOrderProducts(Cart cart, Order order) {
        return cart.getCartItems().stream().map(item -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(item.getProduct());
            orderProduct.setQuantity(item.getQuantity());
            order.addOrderProduct(orderProduct);
            return orderProduct;
        }).collect(Collectors.toSet());
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }


}
