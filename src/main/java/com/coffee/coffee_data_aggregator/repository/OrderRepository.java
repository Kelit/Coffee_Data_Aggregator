package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
