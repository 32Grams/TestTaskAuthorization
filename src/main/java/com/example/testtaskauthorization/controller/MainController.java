package com.example.testtaskauthorization.controller;

import com.example.testtaskauthorization.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@AllArgsConstructor
public class MainController {
    UserService userService;

    @GetMapping("/")
    public String main(Model model) {
        //В аттрибуты модели добавили список всех юзеров
        model.addAttribute("users", userService.getALl());
        return "main";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
