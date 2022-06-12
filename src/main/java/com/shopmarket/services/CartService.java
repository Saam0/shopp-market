package com.shopmarket.services;

import com.shopmarket.models.Cart;
import com.shopmarket.models.CartItem;
import com.shopmarket.models.User;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    Cart getOrCreateCart(String userEmail);

    Cart addItemToCart(Cart cart,Long itemId, Long productId, double quantity);

    Cart removeItemById(Cart cart, Long itemId);

    CartItem findItemById(Long itemId);

    Cart clearCart(Cart cart);

}
