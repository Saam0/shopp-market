package com.shopmarket.models.catalog;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "t_global_type")
public class GlobalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String globalType;

    @OneToMany(mappedBy = "globalType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubType> subTypes;

}
