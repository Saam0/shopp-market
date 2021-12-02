package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;
/**
 * Bill of the {@link Order}.
 */
@Data
@Entity
@Table(name = "t_bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @MapsId
    private Order order;

    private int number;

    private double totalCost;

    private boolean payed = false;

    private String ccNumber;

    @Temporal(TIMESTAMP)
    private Date dateCreated;

}
