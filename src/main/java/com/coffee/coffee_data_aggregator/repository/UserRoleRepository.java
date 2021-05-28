package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}