package controllers;

import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coffee/api")
public class CoffeeDataRestController {

    //Получение списка товаров
    @RequestMapping(value = "/get-coffee", headers = {"application/json"},
    method = RequestMethod.GET)
    public @ResponseBody List<String> getCoffee(){
        List<String> coffee = new ArrayList<>();
        coffee.add("MY");
        return coffee;
    }
}
