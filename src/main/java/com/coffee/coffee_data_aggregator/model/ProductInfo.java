package com.coffee.coffee_data_aggregator.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class ProductInfo implements ComboListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JsonIdentityReference
    @JsonSerialize(as=ComboListItem.class)//!!
    private ProductCategory category;

//    private BigDecimal productPrice;
//    @NotNull
//    @Min(0)
//    private Integer productStock;
//    private String productDescription;
//    private String productIcon;
//    @ColumnDefault("0")
//    private Integer productStatus;
//    @ColumnDefault("0")
//    private Integer categoryType;
//
//    @CreationTimestamp
//    private Date createTime;
//    @UpdateTimestamp
//    private Date updateTime;



//    @Id
//    private String productId;
//    @NotNull
//    private String productName;
//    @NotNull
//    private BigDecimal productPrice;
//    @NotNull
//    @Min(0)
//    private Integer productStock;
//    private String productDescription;
//    private String productIcon;
//    @ColumnDefault("0")
//    private Integer productStatus;
//    @ColumnDefault("0")
//    private Integer categoryType;
//
//    @CreationTimestamp
//    private Date createTime;
//    @UpdateTimestamp
//    private Date updateTime;
//    public ProductInfo() {


}