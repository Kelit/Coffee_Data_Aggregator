package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.SCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SCartRepository extends JpaRepository<SCart, Long>, CrudRepository<SCart,Long> {
}
