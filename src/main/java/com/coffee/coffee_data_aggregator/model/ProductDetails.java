package com.coffee.coffee_data_aggregator.model;

import lombok.Data;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 255)
    private String value;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public ProductDetails(){ }
    public ProductDetails(String name, String value, Product product){
        this.name = name;
        this.value = value;
        this.product = product;
    }
}
