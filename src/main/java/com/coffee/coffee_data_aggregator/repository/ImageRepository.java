package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
    Optional<ImageModel> findById(Long id);
}