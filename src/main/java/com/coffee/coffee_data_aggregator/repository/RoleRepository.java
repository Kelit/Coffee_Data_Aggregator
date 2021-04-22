package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Role;
import com.coffee.coffee_data_aggregator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role,Long>{
    Set<Role> findByUser(User user);
}
