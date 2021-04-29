package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.service.ProductService;
import com.coffee.coffee_data_aggregator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/coffee/api")
public class CoffeeDataRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    // Page products

    //Получение списка товаров
    @RequestMapping(value = "/get-coffee", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public List<String> getCoffee(){
        ProductInfo pi = new ProductInfo();
        pi.setProductPrice(BigDecimal.valueOf(01));
        pi.setProductName(String.valueOf(1));
        pi.setProductDescription(String.valueOf(123));
        pi.setProductStock(1);
        pi.setProductIcon("https://bezkoder.com/wp-content/uploads/2020/05/spring-boot-pagination-filter-example-spring-jpa-pageable-table.png");

        productService.addProduct(pi);
//        List<ProductInfo> coffee = productService.findAllProduct();

        List<String> coffee = new ArrayList<>();
        coffee.add("MY");
        return coffee;

//        return coffee;
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
        coffee.add("MY");
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
        return "Создан пользователь:" + user.getUsername();
    }
}
