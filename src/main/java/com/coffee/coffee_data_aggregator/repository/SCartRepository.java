package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.SCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SCartRepository extends JpaRepository<SCart, Long> {
}
