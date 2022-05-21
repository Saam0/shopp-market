package com.shopmarket.services.impl;

import com.shopmarket.models.Cart;
import com.shopmarket.models.CartItem;
import com.shopmarket.models.Product;
import com.shopmarket.models.User;
import com.shopmarket.repositories.CartItemRepository;
import com.shopmarket.repositories.CartRepository;
import com.shopmarket.services.CartService;
import com.shopmarket.services.ProductService;
import com.shopmarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;


    @Override
    public Cart getOrCreateCart(String userEmail) {
        User user = userService.findUserByEmail(userEmail)
                .orElseThrow(() -> new NullPointerException("No user found with email: " + userEmail));
        Cart cart = new Cart();
        if (user.getCart() == null) {
            cart.setUser(user);
        } else {
            cart = user.getCart();
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart addItemToCart(Cart cart, Long productId, double quantity) {
        CartItem cartItem = new CartItem();

        Product product = productService.findById(productId)
                .orElseThrow(() -> new NullPointerException("No products found with id: " + productId));

        if (isThereProductOnCart(product, cart)) {
            cart.getCartItems().stream()
                    .filter(p -> p.getProduct().equals(product))
                    .forEach(p -> p.setQuantity( quantity));
//                    .forEach(p -> p.setQuantity(p.getQuantity() + quantity));
        } else {
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
//            cart.addCartItem(cartItem);
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemById(Cart cart, Long itemId){
        cart.getCartItems().remove(findItemById(itemId));
        return cartRepository.save(cart);
    }

    @Override
    public Cart clearCart(Cart cart){
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }

    @Override
    public CartItem findItemById(Long itemId){
        return cartItemRepository.findById(itemId).orElseThrow(() -> new NullPointerException("No item found with id: " + itemId));
    }

    private boolean isThereProductOnCart(Product product, Cart cart) {
        return cart.getCartItems().stream().anyMatch(p -> p.getProduct().equals(product));
    }


}
