package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Order of the {@link User}.
 */
@Data
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = OrderProduct.class, mappedBy = "order")
    private Set<OrderProduct> orderProducts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Bill bill;

    private double productsCost;

    @Temporal(TIMESTAMP)
    private Date dateCreated;

    private int deliveryCost;

    private boolean deliveryIncluded;

    private boolean executed;
}
