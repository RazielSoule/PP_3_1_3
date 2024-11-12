package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

@Controller
public class GuestController {
    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;
    private final UserDetailsServiceImp userDetailsServiceImp;
    @Autowired
    public GuestController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp, UserDetailsServiceImp userDetailsServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }
    @GetMapping
    public String index() {
        return "index";
    }
    @GetMapping(value = "/createAdmin")
    public String createAdmin() {
        if (userDetailsServiceImp.loadUserByUsername("admin@adm.in") == null) {
            userServiceImp.addUser(new User("admin", "admin", 999, "admin", "admin@adm.in", roleServiceImp.getRoles()));
        }
        return "redirect:/login";
    }
}
