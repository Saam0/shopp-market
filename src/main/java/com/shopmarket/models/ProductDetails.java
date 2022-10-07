package com.shopmarket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

//@Data
@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "t_product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;

    private String manufacturer;

    private String country;

    private double weight;

    private String typeOfPackaging;

    private String storageConditions; // պահպանման պայմաններ

    private String composition; //բաղադրություն

    private String productDescription;

    @OneToOne(mappedBy = "productDetails")
    private Product product;
}
