package com.example.assjava6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Trả về file login.html trong thư mục templates
    }

    @GetMapping("/register")
    public String RegisterPage() {
        return "register";
    }

}
