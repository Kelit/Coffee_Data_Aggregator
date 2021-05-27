package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}