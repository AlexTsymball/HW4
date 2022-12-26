package com.example.controllers;


import com.example.dto.UserDto;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping()
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login")
                .addObject("user", new UserDto());
    }


    @PostMapping("/login")
    public ModelAndView checkUser(UserDto user, HttpSession session) {
        if (userService.containsUser(user)) {
            String login = user.getLogin();
            userService.setSessionId(login, session.getId());
            session.setAttribute("login", login);
            return new ModelAndView("redirect:welcome");
        } else {
            ModelAndView model = new ModelAndView("login").addObject("user", new UserDto());
            model.addObject("error", "Login or password are incorrect.");
            return model;

        }
    }


}
