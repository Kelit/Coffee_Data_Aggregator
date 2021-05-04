package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    Collection<User> findAllByRole(String role);
}