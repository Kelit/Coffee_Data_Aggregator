package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, String> {

}