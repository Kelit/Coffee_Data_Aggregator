package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
