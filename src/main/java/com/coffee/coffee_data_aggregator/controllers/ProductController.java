package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import com.coffee.coffee_data_aggregator.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController{

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/categories")
    public String listAll(Model model){
        List<ProductCategory> listCategory = productCategoryService.listAll();
        model.addAttribute("listCategories", listCategory);

        return "categories/categories";
    }

    @GetMapping("/categories/new")
    public String newCategory(Model model){
        List<ProductCategory> listCategoryForForm = productCategoryService.listCategoryForForm();

        model.addAttribute("category", new ProductCategory());
        model.addAttribute("listCategories", listCategoryForForm);
//        model.addAttribute("listCategories", listCategory);

        return "categories/category_form";
    }
}
