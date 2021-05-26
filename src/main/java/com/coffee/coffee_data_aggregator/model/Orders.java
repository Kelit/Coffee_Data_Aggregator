package com.coffee.coffee_data_aggregator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "order")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="Order_Data", nullable = false)
    private Date orderDate;
    @Column(name="Order_Num", nullable = false)
    private Date orderNum;
    @Column(name="Amount", nullable = false)
    private double amount;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, //
            foreignKey = @ForeignKey(name = "user_fk"))
    private User customer;

}
