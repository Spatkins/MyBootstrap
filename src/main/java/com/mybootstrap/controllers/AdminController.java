package com.mybootstrap.controllers;


import com.mybootstrap.entities.User;
import com.mybootstrap.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/all")
    public String showAllUsers(Model model, Principal principal) {
        User user = new User();
        List<User> usersList = userService.getList();
        model.addAttribute("addNewUser", user);
        model.addAttribute("allUsers", usersList);
        model.addAttribute("loggedUser",userService.loadUserByUsername(principal.getName()));
        return "admin/all-users";
    }

    @RequestMapping("/all/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/all";
    }

    @RequestMapping("/all/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.remove(id);
        return "redirect:/admin/all";
    }

}
