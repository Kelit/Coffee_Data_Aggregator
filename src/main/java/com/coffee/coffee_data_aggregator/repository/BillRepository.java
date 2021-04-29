package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {

}