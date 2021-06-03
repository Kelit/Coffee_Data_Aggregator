package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractRestController<Product, ProductRepository> {
    public ProductController(ProductRepository repo) { super(repo); }
}
