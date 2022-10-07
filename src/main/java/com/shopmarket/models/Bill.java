package com.shopmarket.models;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;
/**
 * Bill of the {@link Order}.
 */
@Getter
@Setter
//@ToString
@RequiredArgsConstructor
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
