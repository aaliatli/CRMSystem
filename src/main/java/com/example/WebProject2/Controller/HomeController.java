package com.example.WebProject2.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

public abstract class HomeController {
    @RequestMapping("/api")
    public String mainScreen(){
        return "API is working";
    }
}
