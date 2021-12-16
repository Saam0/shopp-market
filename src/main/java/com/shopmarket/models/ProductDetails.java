package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;

    private String manufacturer;

    private String productDescription;

    @OneToOne(mappedBy = "productDetails")
    private Product product;
}
