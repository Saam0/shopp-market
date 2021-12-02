package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "t_user")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;

    private String title;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    enum Roles{
        ROLE_ADMIN,
        ROLE_SUPER_ADMIN,
        ROLE_STAFF,
        ROLE_USER
    }
}

