package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("{productId}")
//    public ProductInfo showOne(@PathVariable("productId") String productId) { return productService.findOne(productId); }
    public String showOne(@PathVariable ProductInfo productId, Model model) {
        model.addAttribute("product_info", productId);
        return "productEdit";
    }
}
