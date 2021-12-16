package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_supplier")
public class Supplier {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;

    private String supplierName;

    private String phoneNumber;

    @OneToOne(mappedBy = "supplier")
    private Stock stock;
}
