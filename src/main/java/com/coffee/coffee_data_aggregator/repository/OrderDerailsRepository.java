package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.OrderDerails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDerailsRepository extends JpaRepository<OrderDerails, Long> {
}