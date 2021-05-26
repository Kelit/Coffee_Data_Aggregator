package com.coffee.coffee_data_aggregator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, //
            foreignKey = @ForeignKey(name = "order_fk"))
    private Orders order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, //
            foreignKey = @ForeignKey(name = "product_fk"))
    private Product product;
    @Column(name = "Price", nullable = false)
    private double price;
    @Column(name = "Amount", nullable = false)
    private double amount;
}
