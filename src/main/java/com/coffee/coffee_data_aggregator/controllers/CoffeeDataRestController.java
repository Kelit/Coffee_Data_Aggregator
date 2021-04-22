package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/coffee/api")
public class CoffeeDataRestController {

    @Autowired
    UserService userService;
    // Page products

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

    // Page prodductOrder
    //Получение списка товаров
    @RequestMapping(value = "/get-orders", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getOrders(){
        List<String> coffee = new ArrayList<>();
        coffee.add("My Order");
        return coffee;
    }
    //Удаление товаров
    @RequestMapping(value = "/del-order", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String delOrder(){

        return "Order has delete";
    }

    @RequestMapping(value = "/create-user",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public String createUser(@RequestBody User user) {
        log.info("Получили данны: " + user.getUsername());
        userService.addUser(user);
        return "Создан пользователь:" + user.getUsername() + " " + user.getPassword();
    }
}
