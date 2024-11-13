package ru.kata.spring.boot_security.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImp;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPanel(ModelMap model, Principal principal) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserDetailsImp userDetails = (UserDetailsImp) authentication.;
        model.addAttribute("activeUser", userService.getUserByUsername(principal.getName()));
        model.addAttribute("usersList", userService.getUsers());
        model.addAttribute("rolesList", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin/admin-page";
    }

    @PostMapping(value = "/add-user")
    public String newUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/admin";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit-user")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("error on editing user");
            return "redirect:/admin";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete-user")
    public String deleteUser(@RequestParam(value = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
