package com.shopmarket.models;


import lombok.*;
import com.shopmarket.validation.PasswordMatches;
import com.shopmarket.validation.ValidEmail;
import com.shopmarket.validation.ValidPassword;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;



@PasswordMatches
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String LastName;

    @ValidEmail
    private String email;


    private String gender;

    private Date birthDate;


    private boolean enabled;

    private Date dateCreated;

    @NotBlank(message = "{message.phone.notEmpty}")
    private String phoneNumber;

    @ValidPassword
    private String password;

    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")})
    private Set<Role> roles;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = true;
    }


}
