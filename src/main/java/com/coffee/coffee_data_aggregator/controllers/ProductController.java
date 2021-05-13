package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractRestController<ProductInfo, ProductRepository> {
    public ProductController(ProductRepository repo) { super(repo); }
}
