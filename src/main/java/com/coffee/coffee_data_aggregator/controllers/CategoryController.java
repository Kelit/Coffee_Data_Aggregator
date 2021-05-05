package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.CategoryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends AbstractRestController<ProductCategory, CategoryRepository> {
    public CategoryController(CategoryRepository repo) {
        super(repo);
    }
}