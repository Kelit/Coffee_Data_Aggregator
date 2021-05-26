package com.coffee.coffee_data_aggregator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role", unique = true)
    private String role;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<User> users;
}