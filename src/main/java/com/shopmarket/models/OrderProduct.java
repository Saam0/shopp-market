package com.shopmarket.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "t_order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //    @MapsId
    @JsonManagedReference
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Order order;

    @JsonManagedReference
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Product product;

    private double quantity;


    public void setOrder(Order order) {
        setOrder(order, false);
    }

    public void setOrder(Order order, boolean otherSideHasBeenSet) {
        this.order = order;
        if (otherSideHasBeenSet) {
            return;
        }
        order.addOrderProduct(this, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderProduct that = (OrderProduct) o;
        return Id != null && Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
