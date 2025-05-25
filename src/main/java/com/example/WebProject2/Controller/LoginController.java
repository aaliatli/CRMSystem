package com.example.WebProject2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebProject2.entity.Login;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String userLogin(@RequestBody Login request) {
        boolean success = loginService.login(request.getUserMail(), request.getUserPassword());
        if(success){
            return "Giriş başarılı";
        }else{
            return "Hatalı kullanıcı adı ya da şifre";
        }
    }
    
}
