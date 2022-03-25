package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Cart of the {@link User}
 */
@Data
@Entity
@Table(name = "t_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @MapsId
    private User user;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = CartItem.class, mappedBy = "cart")
    private List<CartItem> cartItems;

    private boolean deliveryIncluded = true;



    public void addCartItem(CartItem cartItem) {
        addCartItem(cartItem, false);
    }

    public void addCartItem(CartItem cartItem, boolean otherSideHasBeenSet) {
        getCartItems().add(cartItem);
        if (otherSideHasBeenSet) {
            return;
        }
        cartItem.setCart(this, true);
    }

    public void removeCartItem(CartItem cartItem) {
        removeCartItem(cartItem, false);
    }

    public void removeCartItem(CartItem cartItem, boolean otherSideHasBeenSet) {
        getCartItems().remove(cartItem);
        if (otherSideHasBeenSet) {
            return;
        }
        cartItem.removeCart(this, true);
    }


}
