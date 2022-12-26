package com.example.controllers;


import com.example.dto.UserDto;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ModelAndView logout(HttpSession session) {
        userService.removeSessionId(String.valueOf(session.getAttribute("login")), session.getId());
        session.removeAttribute("login");
        return new ModelAndView("redirect:/app/login").addObject("user", new UserDto());
    }

}
