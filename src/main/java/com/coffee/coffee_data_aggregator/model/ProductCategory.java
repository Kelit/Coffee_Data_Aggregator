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


        @Transient
        private boolean hasChildren;

        public boolean isHasChildren(){
                return hasChildren;
        }

        public ProductCategory(){}

        public static ProductCategory copyFull(ProductCategory category){
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(category.getId());
                productCategory.setName(category.getName());
                productCategory.setAlias(category.getAlias());
                productCategory.setImage(category.getImage());
                productCategory.setActive(category.isActive());
                productCategory.setHasChildren(category.getChildren().size() > 0);

                return productCategory;
        }

        public static ProductCategory copyFull(ProductCategory category, String name){
                ProductCategory copyCategory = ProductCategory.copyFull(category);
                copyCategory.setName(name);
                return copyCategory;
        }

        public static ProductCategory copyIdAndName(ProductCategory category){
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(category.getId());
                productCategory.setName(category.getName());

                return productCategory;
        }
        public static ProductCategory copyIdAndName(Long id, String name){
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(id);
                productCategory.setName(name);

                return productCategory;
        }

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