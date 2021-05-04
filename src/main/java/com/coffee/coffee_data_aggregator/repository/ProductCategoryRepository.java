package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);
    List<ProductCategory> findAllByOrderByCategoryType();
    ProductCategory findByCategoryType(Integer categoryType);
}