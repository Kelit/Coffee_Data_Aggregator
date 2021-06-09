package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.config.UserSecurity;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    @Autowired
    private UserService service;

    @GetMapping("/account")
    public String viewDetails(@AuthenticationPrincipal UserSecurity loggedUser,
                              Model model){
        String email = loggedUser.getUsername();
        // TODO Truble
        User user = service.getByEmail(email);
//        model.addAttribute("user", user);
        return "account_form";
    }

    @PostMapping("/account/update")
    public String saveDetils(User user,
//                             RedirectAttributes redirectAttributes
                             @AuthenticationPrincipal UserSecurity userSecurity,
                             @RequestParam("image") MultipartFile multipartFile){
        User savedUser = service.updateUser(user);
        // if user = null service.save
        userSecurity.setFirstName(user.getFirstName());
        userSecurity.setLastName(user.getLastName());
        return "user save";
    }
}
