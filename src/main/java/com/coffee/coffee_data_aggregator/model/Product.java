package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="product")
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "volume")
    @NotNull
    private Integer volume;

    @Column(name = "stock")
    @NotNull
    private Integer productStock;

    @Column(name = "icon")
    @NotNull
    private String productIcon;

    @ColumnDefault("0")
    private Integer productStatus;
    //!!
//    @ColumnDefault("0")
//    private Integer categoryType;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
