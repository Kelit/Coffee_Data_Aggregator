package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
}