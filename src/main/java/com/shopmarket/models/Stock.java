package com.shopmarket.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

}
