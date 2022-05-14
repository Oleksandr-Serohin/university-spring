package ua.com.university.universityspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.university.universityspring.entity.User;
import ua.com.university.universityspring.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public record UserController(UserServiceImpl userService) {

    @GetMapping
    public String userList(Model model){
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/users/list_users";
    }


}
