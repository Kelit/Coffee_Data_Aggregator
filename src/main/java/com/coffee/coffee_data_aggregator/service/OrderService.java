package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.repository.OrderRepository;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

//    @Transactional
//    public void saveOrder()


}
