package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.message.ResponseUser;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import com.coffee.coffee_data_aggregator.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get-users")
    @ResponseBody
    public List<ResponseUser> getUsers(){
        return userService.findAllUsers().stream().map(str ->
               new ResponseUser(String.valueOf(str.getId()),
                                   str.getUsername(),
                                   str.getEmail(),
                                   str.getName(),
                                   str.getLastName(),
                                   str.getPhone(),
                                   String.valueOf(str.getActive())
                )).collect(Collectors.toList());
    }

    @GetMapping
    public Page<User> list(@PageableDefault Pageable pageable) { return userService.findAll(pageable); }

//    @GetMapping("{id}")
//    public T getOne(@PathVariable("id") T obj) { return obj; }
    @PostMapping
    public @ResponseBody String add(@Valid @RequestBody User user) {

        userService.saveUser(user,"USER_ROLE");
        return "User save";
    }

//    @PutMapping("{id}")
//    public T update(@PathVariable("id") T dbObj, @RequestBody T obj) {
//        BeanUtils.copyProperties(obj, dbObj, "id");
//        return repo.save(dbObj);
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") T dbObj) { repo.delete(dbObj); }
}