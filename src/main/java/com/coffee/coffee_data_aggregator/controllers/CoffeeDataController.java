package com.coffee.coffee_data_aggregator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class CoffeeDataController {
    @RequestMapping("/")
    public String start(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/products")
    public String getProducts(){
        return "products";
    }

    @GetMapping("/orders")
    public String getOrders(){
        return "orders";
    }
}
