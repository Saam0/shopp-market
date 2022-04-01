package com.shopmarket.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date purchaseDate;

    @NotBlank(message = "{message.validation.noteBlank}")
    private String unitOfMeasurement;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

//    @OneToOne(cascade = CascadeType.ALL)
//    private Supplier supplier;


}
