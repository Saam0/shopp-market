package com.shopmarket.models;

import com.shopmarket.models.catalog.Type;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Table(name = "t_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true, nullable = false)
    private String codeUUID = UUID.randomUUID().toString();

    private String productName;

    private boolean available;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductDetails productDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private Picture picture;

    @Valid
    @OneToOne(mappedBy = "product")
    private Stock stock;

    @Valid
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "type_id")
    private Type type;



//    @ManyToOne(fetch = FetchType.LAZY)
//    private Type type;

}
