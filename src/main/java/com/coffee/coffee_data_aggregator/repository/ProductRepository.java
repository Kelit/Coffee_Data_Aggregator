package com.coffee.coffee_data_aggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {
}
