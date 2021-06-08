package com.coffee.coffee_data_aggregator.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false, unique = true)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @Override
    public String toString(){
        return this.name;
    }
}