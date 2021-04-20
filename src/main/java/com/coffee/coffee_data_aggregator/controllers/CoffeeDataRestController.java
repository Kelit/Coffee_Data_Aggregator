package com.coffee.coffee_data_aggregator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/coffee/api")
public class CoffeeDataRestController {

    //Получение списка товаров
    @RequestMapping(value = "/get-coffee", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getCoffee(){
        List<String> coffee = new ArrayList<>();
        coffee.add("MY");
        return coffee;
    }

    //Удаление товаров
    @RequestMapping(value = "/del-coffee", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String delCoffee(){

        return "Item has delete";
    }
}
