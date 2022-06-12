package com.shopmarket.models;

import com.shopmarket.models.catalog.Type;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
//    @ToString.Exclude
    private ProductDetails productDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private Picture picture;

    @Valid
    @OneToOne(mappedBy = "product")
    private Stock stock;

//    @JsonBackReference
    @Valid
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "type_id")
    private Type type;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return Id != null && Objects.equals(Id, product.Id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
