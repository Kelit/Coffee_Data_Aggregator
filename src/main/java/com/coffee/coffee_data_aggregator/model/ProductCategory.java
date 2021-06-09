package com.coffee.coffee_data_aggregator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "categories")
public class ProductCategory{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 100, nullable = false, unique = true)
        private String name;
        @Column(length = 100, nullable = false, unique = true)
        private String alias;
        @Lob
        private String image;

        private boolean active;

        @OneToOne
        @JoinColumn(name = "parent_id")
        private ProductCategory parent;

        @OneToMany(mappedBy = "parent")
        private Set<ProductCategory> children = new HashSet<>();

        public ProductCategory(){}

        public ProductCategory(Long id){
                this.id = id;
        }

        public ProductCategory(String name){
                this.name = name;
                this.alias = name;
        }
        public ProductCategory(String name, ProductCategory parent){
                this(name);
                this.parent = parent;
        }
}