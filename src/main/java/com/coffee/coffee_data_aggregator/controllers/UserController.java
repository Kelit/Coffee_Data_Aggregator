package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Role;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import com.coffee.coffee_data_aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        List<Role> listRole = service.listRoles();

        User user = new User();
        user.setActive(true);

        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRole);

        return "user add";
    }

    @GetMapping("/user/save")
    public String saveUser(User user,
                          @RequestParam("image")MultipartFile multipartFile){

        service.save(user);
        return "user save";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable(name = "id") Long id){
        User user = service.getUser(id);
        // TODO Create user form and Castom Exaption
        return "user_form";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id){
        User user = service.getUser(id);

        service.deleteUser(id);
        return "uder delete";
    }

    @GetMapping("/users/{id}/enable/{active}")
    public String updateActive(@PathVariable("id") Long id,
                               @PathVariable("active") boolean active){
        service.updateUserActive(id,active);
        String status = active ? "true" : "false";
        String messag = "User id" + id + " has" + status;
        // TODO REddicetAttributes
//        return "redrect:/users";
        return "users";
    }


    @PostMapping("users/check-mail")
    public String checkDuplicate(@Param("email") String email){
        return  service.isEmailUnique(email) ? "Ok email" : "NO Please change email";
    }

}