package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractRestController<ProductInfo, ProductRepository> {
    public ProductController(ProductRepository repo) {
        super(repo);
    }
}
