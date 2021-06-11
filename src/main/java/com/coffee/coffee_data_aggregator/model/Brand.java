package com.coffee.coffee_data_aggregator.model;

import lombok.Data;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 45, unique = true)
    private String name;

    private String icon;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "brands_categories",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<ProductCategory> category = new HashSet<>();

    public Brand(){}
    public Brand(String name){ this.name = name; }

    @Override
    public String toString(){
        return "Brand" + id +" , name = " + name + " ,categories = " + category;
    }

}
