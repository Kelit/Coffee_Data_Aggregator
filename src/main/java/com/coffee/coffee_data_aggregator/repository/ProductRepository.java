package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Brand;
import com.coffee.coffee_data_aggregator.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("select  bp from Brand bp order by bp.name ASC")
    List<Product> findAll();
    Product findByName(String name);

    @Query("select  bp from Brand bp where bp.name like %?1%")
    Page<Brand> findAll(String key, Pageable pegeable);
}