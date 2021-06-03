package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Page<User> findAll(Pageable pageable);
    User findByUsername(String username);
}