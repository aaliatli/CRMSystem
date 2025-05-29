package com.example.WebProject2.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class HomeController {
    public String welcome(){
        return "Hello";
    }
}
