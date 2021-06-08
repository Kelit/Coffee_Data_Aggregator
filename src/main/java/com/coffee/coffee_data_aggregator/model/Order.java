package com.coffee.coffee_data_aggregator.model;

import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "order_table")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productOrder;

    private Date dateCreated;

    private String status;

    private Integer orderNum;

    @Column(name = "Quanity", nullable = false)
    private int quanity;
//    @Column(name = "Price", nullable = false)
//    private double price;
    @Column(name = "Amount", nullable = false)
    private double amount;

    @Transient
    public double getSubtotal(){
        return this.productOrder.getProductPrice() * quanity;
    }
}
