package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll(){ return  productRepository.findAll(); }
}
