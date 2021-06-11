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
}
