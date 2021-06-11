package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
    Brand findByName(String name);
    List<Brand> findAll();
    Long countById(Long id);

}