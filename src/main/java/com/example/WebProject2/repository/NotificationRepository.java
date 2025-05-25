package com.example.WebProject2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebProject2.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
    List<Notification> findByService(String service);

    public void deleteByName(String name);
}