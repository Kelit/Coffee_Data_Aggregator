package com.coffee.coffee_data_aggregator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class Maincontroller {
    @RequestMapping("/")
    public String start(){
        return "redirect:/index";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
