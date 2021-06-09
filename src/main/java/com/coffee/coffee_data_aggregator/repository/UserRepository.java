package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUsersByEmail(String email);

    @Query("UPDATE User u set u.active =?2 where u.id = ?1")
    @Modifying
    void updateActive(Long id, boolean enable);
}