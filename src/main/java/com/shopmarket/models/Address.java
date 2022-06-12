package com.shopmarket.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Address of the {@link User}
 *
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "t_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "{message.validation.noteBlank}")
    private String phoneNumber;

    @NotBlank(message = "{message.validation.noteBlank}")
    private String country;         // Country ex:Armenia

    private String province;        // District/County/Province/State ex:Armavir

    private String city;            // City/Town ex:Echmiatsin

    private String addressLine;     // ex: 18/1 Araratyan Street

    private String postalCode;      //  Postal Code/Zip Code ex:1101

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return Id != null && Objects.equals(Id, address.Id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
