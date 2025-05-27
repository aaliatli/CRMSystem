package com.example.WebProject2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
        Login user = loginService.getByCredentials(request.getUserMail(), request.getUserPassword());
        if(user != null){
            return user.getUserMail();
        }else{
            return "Hatalı kullanıcı adı ya da şifre";
        }
    }
    
    @GetMapping("/login")
    public List<Login> getAllUsers(){
        return loginService.getAllUsers();
    }
}
