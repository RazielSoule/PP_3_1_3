package ru.kata.spring.boot_security.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(ModelMap model) {
        model.addAttribute("usersList", userService.getUsers());
        return "admin/users";
    }

    @GetMapping(value = "/add-user")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "admin/add-user";
    }

    @PostMapping(value = "/add-user")
    public String newUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-user";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit-user")
    public String editUser(@RequestParam(value = "id") int id, ModelMap model){
        model.addAttribute("UserId", id);
        model.addAttribute("user", userService.getUser(id));
        return "admin/edit-user";
    }

    @PostMapping(value = "/edit-user-{id}")
    public String editUser(@PathVariable(value = "id") int id, @ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit-user";
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete-user")
    public String deleteUser(@RequestParam(value = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
