package com.example.WebProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebProject2.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{
    Login findByUserMail(String userMail);
}
