package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/userlist")
public class UserManagerController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String userList(Model model){
        log.info("GET USERLIST");
        model.addAttribute("users", userService.findAllUsers());
        return "userlist";
    }

    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getRoleList(user));
        return "userEdit";
    }

    @DeleteMapping(value = "delete/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable String id)
    {
        userService.deletUser(Long.valueOf(id));
        return "User delete";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
//        List<String> role = form.entrySet().stream().filter(v -> v.getValue().equals("on"))
//                .map(Map.Entry::getKey).collect(Collectors.toList());
//        String active = form.entrySet().stream().filter(k -> k.getKey().equals("active")).map(Map.Entry::getValue).findFirst().get();
//
//        log.info(form + "KeyForm");
//        log.info(form.values() + "ValueForm");
//        log.info(role + "RoleString");
//        log.info(active + "ActiveString");
//
//        if(user.getUsername() != username) user.setUsername(username);
//        user.setActive(Boolean.parseBoolean(active));//true false
//        userService.saveUser(user, role);
        return "redirect:/userlist";
    }
}
