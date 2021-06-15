package com.coffee.coffee_data_aggregator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String name;
    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;

    public ProductImage(){}
    public ProductImage(String name, Product product){ this.name = name; this.product = product; }
}
