package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.CartItem;
import com.coffee.coffee_data_aggregator.model.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
}