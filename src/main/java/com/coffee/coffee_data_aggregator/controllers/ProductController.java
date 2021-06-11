package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import com.coffee.coffee_data_aggregator.service.ProductCategoryService;
import com.coffee.coffee_data_aggregator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController{
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String listAll(Model model){
        List<Product> productList = productService.listAll();

        model.addAttribute("listProducts", productList);

        return "products/products";
    }
}
