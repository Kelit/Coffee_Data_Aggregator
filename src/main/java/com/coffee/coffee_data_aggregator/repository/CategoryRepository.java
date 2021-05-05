package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
}
