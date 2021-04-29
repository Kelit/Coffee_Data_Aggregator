package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.OrderedProduct;
import com.coffee.coffee_data_aggregator.model.OrderedProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, OrderedProductId> {
}