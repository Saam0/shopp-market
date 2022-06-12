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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @Transactional
    @Override
    public Order saveOrder(String userEmail) {
        User user = userService.findUserByEmail(userEmail).get();
        Cart cart = user.getCart();
        Order order = new Order();
        order.setUser(user);
        Set<OrderProduct> orderProducts = cart.getCartItems().stream().map(item -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(item.getProduct());
            orderProduct.setQuantity(item.getQuantity());
            order.addOrderProduct(orderProduct);
            return orderProduct;
        }).collect(Collectors.toSet());

        // TODO: 12.06.2022 avelacnel Orderi pnacac toxery

        return orderRepository.save(order);
    }

    // TODO: 12.06.2022 kapel Bill@ ordery het
//    public Order createOrder() {
//
//    }
//
//    public void createOrderedProduct(){
//
//    }
}
