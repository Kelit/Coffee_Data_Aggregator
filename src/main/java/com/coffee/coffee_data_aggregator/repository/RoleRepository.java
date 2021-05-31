package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}