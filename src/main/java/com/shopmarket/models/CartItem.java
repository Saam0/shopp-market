package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*    @MapsId("Id")
    @JoinColumn(name = "cart_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)*/

    @MapsId
    @JoinColumn(name = "cart_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cart cart;

    /* @MapsId("Id")
     @JoinColumn(name = "product_id", referencedColumnName = "id")
     @ManyToOne(optional = false, fetch = FetchType.EAGER)*/
    @MapsId
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;

    private double quantity;



    public void setCart(Cart cart) {
        setCart(cart,false);
    }

    public void setCart(Cart cart, boolean otherSideHasBeenSet) {
        this.cart = cart;
        if (otherSideHasBeenSet) {
            return;
        }
        cart.addCartItem(this, true);
    }
    public void removeCart(Cart cart) {
        removeCart(cart,false);
    }
    public void removeCart(Cart cart, boolean otherSideHasBeenSet) {
        this.cart = null;
        if (otherSideHasBeenSet) {
            return;
        }
        cart.removeCartItem(this, true);
    }
}
