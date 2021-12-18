package com.shopmarket.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}

