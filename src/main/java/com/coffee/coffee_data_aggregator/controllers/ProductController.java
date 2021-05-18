package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.message.ResponseProduct;
import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import com.coffee.coffee_data_aggregator.service.ProductStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractRestController<ProductInfo, ProductRepository> {
    public ProductController(ProductRepository repo) { super(repo); }

    @Autowired
    private ProductStorageService productStorageService;

    @GetMapping("/list_product")
    public @ResponseBody List<ResponseProduct> listProduct() {
        List<ResponseProduct> products = productStorageService.getAllFiles().map(
                dbProduct -> new ResponseProduct(
                        String.valueOf(dbProduct.getId()),
                        dbProduct.getName(),
                        dbProduct.getCategory().getName(),
                        String.valueOf(dbProduct.getProductStock()),
                        String.valueOf(dbProduct.getProductPrice()),
                        dbProduct.getProductDescription(),
                        String.valueOf(dbProduct.getCreateTime()),
                        String.valueOf(dbProduct.getUpdateTime()),
                        dbProduct.getProductIcon().getPicByte())
                ).collect(Collectors.toList());

        return products;
    }

}
