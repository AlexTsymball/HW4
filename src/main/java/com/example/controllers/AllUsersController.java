package com.example.controllers;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class AllUsersController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ModelAndView tableUsers() {
        return new ModelAndView("users").addObject("usersList", userService.getAllUsers());
    }
}
