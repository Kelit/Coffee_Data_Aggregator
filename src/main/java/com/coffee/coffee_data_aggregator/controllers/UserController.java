package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractRestController<User, UserRepository> {
        public UserController(UserRepository repo) {
            super(repo);
        }

}