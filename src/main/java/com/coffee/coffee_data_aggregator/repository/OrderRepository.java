package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Order;
import com.coffee.coffee_data_aggregator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserAccountOrderByDateCreatedDesc(User account);
}