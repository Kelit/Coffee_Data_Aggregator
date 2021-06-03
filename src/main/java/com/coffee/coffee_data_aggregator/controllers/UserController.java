package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.message.ResponseUser;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractRestController<User, UserRepository> {

    public UserController(UserRepository repo) {
        super(repo);
    }
}