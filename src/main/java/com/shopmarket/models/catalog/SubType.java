package com.shopmarket.models.catalog;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_sub_type")
public class SubType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subTypeName;

    @ManyToOne(fetch = FetchType.EAGER)
    private GlobalType globalType;

    @OneToMany(mappedBy = "subType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Type> types;

}
