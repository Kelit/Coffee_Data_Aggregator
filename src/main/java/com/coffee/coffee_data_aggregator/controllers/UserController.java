package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.message.ResponseUser;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.service.UserService;
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
                                   str.getLastname(),
                                   str.getPhone(),
                                   str.getPassword(),
                                   String.valueOf(str.getActive())
                )).collect(Collectors.toList());
    }

    @GetMapping
    public Page<User> list(@PageableDefault Pageable pageable) { return userService.findAll(pageable); }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") Long obj) { return userService.findById(obj); }

    @PostMapping
    public @ResponseBody String add(@RequestBody ResponseUser user) {
        User usr = Stream.of(user).map( srt -> new User(
            user.getName(),
            user.getLastname(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getPhone(),
            user.getActive()
        )).findFirst().get();
        userService.addUser(usr);
        return "user save";
    }

//    @PutMapping("{id}")
//    public T update(@PathVariable("id") T dbObj, @RequestBody T obj) {
//        BeanUtils.copyProperties(obj, dbObj, "id");
//        return repo.save(dbObj);
//    }
    @PutMapping("{id}")
    public @ResponseBody String update(@PathVariable("id") Long dbObj, @RequestBody ResponseUser user) {
        User usr = userService.findById(dbObj);
        User obj = Stream.of(user).map( srt -> new User(
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getActive()
        )).findFirst().get();
        BeanUtils.copyProperties(obj, usr, "id");
        userService.saveUser(usr);
        return "user update";
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long dbObj) {
        userService.deleteUser(dbObj);
    }
}