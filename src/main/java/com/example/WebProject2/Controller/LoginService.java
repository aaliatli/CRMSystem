package com.example.WebProject2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject2.entity.Login;
import com.example.WebProject2.repository.LoginRepository;

@Service
public class LoginService{

    @Autowired
    private LoginRepository loginRepo;

    
    public boolean login(String userMail, String userPassword){
        Login user = loginRepo.findByUserMail(userMail);
        return user != null && user.getUserPassword().equals(userPassword);
    }

}