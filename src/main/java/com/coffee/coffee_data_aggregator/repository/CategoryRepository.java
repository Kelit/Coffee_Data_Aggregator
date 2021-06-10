package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<ProductCategory, Long> {
//public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("SELECT pc from ProductCategory pc where  pc.parent.id is NULL")
    @Modifying
    List<ProductCategory> findRootCategory(Sort sort);
    ProductCategory findByName(String name);
    ProductCategory findByAlias(String alies);
    Long countById(Long id);

    @Query("update ProductCategory pc set pc.active = ?2 where pc.id = ?1")
    @Modifying
    void updateActiveStatus(Long id,  boolean active);
}