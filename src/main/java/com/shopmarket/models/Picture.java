package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String smPictureName;

    private String mdPictureName;

    private String lgPictureName;

    @OneToOne(mappedBy = "picture")
    private Product product;
}
