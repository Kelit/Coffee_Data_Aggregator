package com.coffee.coffee_data_aggregator.model;

import com.coffee.coffee_data_aggregator.util.ComboListItem;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.util.*;
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

    @Lob
    @Column(name = "main_icon", nullable = false)
    private String mainIcon;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductDetails> details = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductImage> images = new HashSet<>();

    public void addExtraImage(String imageName){
        this.images.add(new ProductImage(imageName, this));
    }

    public void addDetail(String name, String value){
        this.details.add(new ProductDetails(name, value, this));
    }
    public void addDetail(Long id, String name, String value){
        this.details.add(new ProductDetails(id, name, value, this));
    }

    @Transient
    public String getMainImagePath(){
        if(id == null || mainIcon == null) return  "" ;//TODO !!! Return default base 64

        return this.mainIcon;
    }
}