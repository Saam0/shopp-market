package com.shopmarket.models.catalog;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_global_type")
public class GlobalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String globalTypeName;

    @OneToMany(mappedBy = "globalType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubType> subTypes;

}
