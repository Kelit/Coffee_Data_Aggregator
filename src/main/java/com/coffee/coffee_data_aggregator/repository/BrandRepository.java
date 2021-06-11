package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
    Brand findByName(String name);
    Long countById(Long id);

    @Query("select bp from  Brand bp where bp.name like %?1%")
    Page<Brand> findAll(String key, Pageable pag);
    @Query("select NEW Brand(bp.id, bp.name) from Brand bp ORDER BY bp.name ASC")
    List<Brand> findAll();
}