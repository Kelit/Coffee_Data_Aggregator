package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Role;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import com.coffee.coffee_data_aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController{
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> listAll(){ return service.listAll(); }

    @GetMapping("/users/new")
    public String newUser(Model model){
        List<Role> listRole = service.listRoles;

        User user = new User();
        user.setActive(true);

        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRole);

        return "user add";
    }

    @GetMapping("/user/save")
    public String saveUser(User user){
        service.save(user);
        return "user save";
    }

}