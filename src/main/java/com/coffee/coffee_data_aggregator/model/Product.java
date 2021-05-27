package com.coffee.coffee_data_aggregator.model;

import com.coffee.coffee_data_aggregator.util.ComboListItem;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Data
@Table(name = "product_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JsonIdentityReference
    private ProductCategory category;

    private double productPrice;

    private String productDescription;

//    @ManyToOne
//    @JsonIdentityReference
//    @JsonSerialize(as=ComboListItem.class)
//    private ImageModel productIcon;

    @Lob
    private byte[] icon;
    private String typeIcon;

    @CreationTimestamp
    private Date createTime;

}