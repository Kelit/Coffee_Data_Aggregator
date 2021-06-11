package com.coffee.coffee_data_aggregator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String value;
}
