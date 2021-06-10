package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/caegories/check_unique")
    public String checkUnique(
                                @Param("id") Long id,
                                @Param("name") String name,
                                @Param("alias") String alias
                            ){
        return productCategoryService.checkUnique(id, name, alias);
    }
}

