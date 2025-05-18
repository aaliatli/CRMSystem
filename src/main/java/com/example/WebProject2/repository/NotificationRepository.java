package com.example.WebProject2.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebProject2.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
}