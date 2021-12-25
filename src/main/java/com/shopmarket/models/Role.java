package com.shopmarket.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}

