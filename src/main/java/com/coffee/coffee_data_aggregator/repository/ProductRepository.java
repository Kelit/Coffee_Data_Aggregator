package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {
    @Override
    @EntityGraph(attributePaths = {"category"})
    Page<ProductInfo> findAll(Pageable pageable);
}