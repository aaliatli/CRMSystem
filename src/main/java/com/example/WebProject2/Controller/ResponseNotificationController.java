package com.example.WebProject2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebProject2.entity.Notification;
import com.example.WebProject2.entity.StoreNotification;
import com.example.WebProject2.repository.NotificationRepository;
import com.example.WebProject2.repository.StoreNotificationRepository;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins="*")
public class ResponseNotificationController {

    @Autowired
    public NotificationRepository notificationRepo;

    @Autowired
    public StoreNotificationRepository storeRepo;

    @GetMapping("/notification")
    public List<Notification> getAllAnalysis(){
        return notificationRepo.findAll();
    }

    @GetMapping("/store")
    public List<StoreNotification> getAllOtherAnalysis(){
        return storeRepo.findAll();
    }
}
