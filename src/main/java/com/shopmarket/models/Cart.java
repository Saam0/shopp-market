package com.shopmarket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cart of the {@link User}
 */
@Getter
@Setter
//@ToString
//@RequiredArgsConstructor
@Entity
@Table(name = "t_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @MapsId
    private User user;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = CartItem.class, mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    private boolean deliveryIncluded = true;

    @Transient
    private double totalPrice;
    @Transient
    private int itemsNumber;
    @Transient
    private boolean empty;

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }


    public double getTotalPrice() {
        double sum = 0.0;
        for (CartItem cartItem : this.cartItems) {
            sum += cartItem.getProduct().getStock().getPurchasePrice() * cartItem.getQuantity();
        }
        return sum;
    }

    public int getItemsNumber() {
        int sum = 0;
        for (CartItem cartItem : this.cartItems) {
            sum += cartItem.getQuantity();
        }
        return sum;
    }


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
