package com.coffee.coffee_data_aggregator.model;

import com.coffee.coffee_data_aggregator.util.ComboListItem;
import com.coffee.coffee_data_aggregator.util.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        scope = ProductCategory.class,
//        resolver = EntityIdResolver.class,
//        property = "id"
//)
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductCategory{
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)//!!
        private Long id;
        private String name;
}