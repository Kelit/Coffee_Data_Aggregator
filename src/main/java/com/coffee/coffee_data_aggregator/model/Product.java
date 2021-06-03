package com.coffee.coffee_data_aggregator.model;

import com.coffee.coffee_data_aggregator.util.ComboListItem;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.util.Date;
import javax.persistence.*;

@Entity
@Data
@Table(name = "product_table")
public class Product implements ComboListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Boolean newProduct;
    private Boolean hotProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_type_id")
    private ProductCategory category;

    private double productPrice;
    private double oldProductPrice;

    @Type(type="text")
    private String productDescription;

    @Lob
    private String icon;

    @CreationTimestamp
    private Date createTime;
}