package com.coffee.coffee_data_aggregator.model;

import com.coffee.coffee_data_aggregator.util.ComboListItem;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.util.Date;
import javax.persistence.*;

@Entity
@Data
@Table(name = "products")
public class Product implements ComboListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 256, nullable = false)
    private String name;
    @Column(unique = true, length = 256, nullable = false)
    private String alias;
    @Column(length = 512, nullable = false, name = "short_description")
    private String shortDescription;
    @Column(length = 4096, nullable = false, name = "full_description")
    private String fullDescription;

    @Column(name = "created_time")
    private Date createdTime;
    @Column(name = "updated_time")
    private Date updatedTime;

    private boolean active;
    @Column(name = "stock")
    private boolean inStock;

    private Double cost;
    private Double price;

    @Column(name = "discount_percent")
    private Double discountPercent;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}