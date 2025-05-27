package com.example.WebProject2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject2.entity.Login;
import com.example.WebProject2.repository.LoginRepository;

@Service
public class LoginService{

    @Autowired
    private LoginRepository loginRepo;

    
    public Login getByCredentials(String mail, String password) {
    Login user = loginRepo.findByUserMail(mail);
    if (user != null && user.getUserPassword().equals(password)) {
        return user;
    }
    return null;
    }

    public List<Login> getAllUsers(){
        return loginRepo.findAll();
    }

}