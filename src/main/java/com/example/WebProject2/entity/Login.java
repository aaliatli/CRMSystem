package com.example.WebProject2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Login{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userMail;
    private String userPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}