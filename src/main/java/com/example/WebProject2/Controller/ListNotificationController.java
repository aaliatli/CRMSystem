package com.example.WebProject2.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebProject2.entity.Notification;
import com.example.WebProject2.entity.StoreNotification;
import com.example.WebProject2.repository.NotificationRepository;
import com.example.WebProject2.repository.StoreNotificationRepository;

import jakarta.persistence.criteria.Predicate;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins="*")
public class ListNotificationController {

    @Autowired
    public NotificationRepository notificationRepo;

    @Autowired
    public StoreNotificationRepository storeRepo;

    @GetMapping("/notification")
    public List<Notification> getAllAnalysis( String service){
        if(service!= null){
            return notificationRepo.findByService(service);
        }
        return notificationRepo.findAll();
    }

    @GetMapping("/notification/all")
    public List<Notification> getFilteredNotifications(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String phone,
        @RequestParam(required = false) String mail){

        return notificationRepo.findAll((root, query, cb) -> {
            List<Predicate> filters = new ArrayList<>();
            if(name != null && !name.isEmpty()){
                filters.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if(phone != null && !phone.isEmpty()){
                filters.add(cb.like(root.get("phone"), "%" + phone + "%"));
            } 

            if(mail != null && !mail.isEmpty()){
                filters.add(cb.like(cb.lower(root.get("mail")), "%" + mail.toLowerCase() + "%" ));
            }

            return cb.and(filters.toArray(new Predicate[0]));
        });
    }

    @GetMapping("/store")
    public List<StoreNotification> getAllOtherAnalysis(){
        return storeRepo.findAll();
    }

    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable Long id){
        notificationRepo.deleteById(id);
        return ResponseEntity.ok().build();

    }

}
