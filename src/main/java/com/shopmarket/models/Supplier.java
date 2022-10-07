package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;


    @NotEmpty
    private String supplierName;

    private String phoneNumber;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Stock> stocks = new ArrayList<>();

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

//    @OneToOne(mappedBy = "supplier")
//    private Stock stock;
}
