package com.mybootstrap.controllers;



import com.mybootstrap.entities.User;
import com.mybootstrap.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String startPage() {

        return "welcome";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String printWelcome(Model model) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
        model.addAttribute("user", user);
        return "user/hellouser";
    }

    @RequestMapping(value = "denied")
    public String errorPage() {
        return "denied";
    }
}
