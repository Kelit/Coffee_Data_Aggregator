package com.coffee.coffee_data_aggregator.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        scope = ProductCategory.class,
        resolver = EntityIdResolver.class,
        property = "id"
)
public class ProductCategory implements ComboListItem {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        private Long id;
        private String name;
    //    @Id
//    @GeneratedValue
//    private Integer categoryId;
//
//    private Integer id;
//
//    private String categoryName;
//
//    @NaturalId
//    private Integer categoryType;
//
//    private Date createTime;
//
//    private Date updateTime;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//
//    public ProductCategory() {
//    }
//
//    public ProductCategory(String categoryName, Integer categoryType) {
//        this.categoryName = categoryName;
//        this.categoryType = categoryType;
//    }
}