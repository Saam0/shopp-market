package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Address of the {@link User}
 *
 */
@Data
@Entity
@Table(name = "t_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "{message.validation.noteBlank}")
    private String country;         // Country ex:Armenia

    private String province;        // District/County/Province/State ex:Armavir

    private String city;            // City/Town ex:Echmiatsin

    private String addressLine;     // ex: 18/1 Araratyan Street

    private String postalCode;      //  Postal Code/Zip Code ex:1101

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
