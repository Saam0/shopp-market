package com.shopmarket.models;


import com.shopmarket.validation.PasswordMatches;
import com.shopmarket.validation.ValidEmail;
import com.shopmarket.validation.ValidPassword;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
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
    @Column(nullable = false)
    private Long id;

    @NotBlank(message = "{message.validation.noteBlank}")
    private String firstName;

    @NotBlank(message = "{message.validation.noteBlank}")
    private String lastName;

    @ValidEmail
    private String email;


    private String gender;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;


    private boolean enabled;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCreated;

    @NotBlank(message = "{message.validation.noteBlank}")
    private String phoneNumber;

    @ValidPassword
    private String password;

    @Transient
    private String passwordConfirm;

    @Valid
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

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
