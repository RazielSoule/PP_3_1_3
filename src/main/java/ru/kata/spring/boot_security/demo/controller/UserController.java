package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.security.UserDetailsImp;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @GetMapping()
    public String user(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        return "user/show";
    }
}
