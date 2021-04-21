package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}