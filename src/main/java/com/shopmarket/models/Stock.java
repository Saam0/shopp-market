package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private double quantity;

    private double purchasePrice;

    private Date purchaseDate;

    private String unitOfMeasurement;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

}
