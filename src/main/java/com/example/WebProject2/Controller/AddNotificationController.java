package com.example.WebProject2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebProject2.entity.Notification;
import com.example.WebProject2.entity.StoreNotification;
import com.example.WebProject2.repository.NotificationRepository;
import com.example.WebProject2.repository.StoreNotificationRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AddNotificationController {

    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    private StoreNotificationRepository storeRepo;

    @PostMapping("/notifications")
    public ResponseEntity<?> create(@RequestBody Notification notification) {
        try {
            if ("Mağaza Lokasyon".equalsIgnoreCase(notification.getService())) {
                StoreNotification store = new StoreNotification();
                store.setStoreLocation(notification.getStoreLocation());
                store.setDeskLocation(notification.getDeskLocation());
                storeRepo.save(store);
                return ResponseEntity.ok("Mağaza lokasyon bildirimi kaydedildi.");
            }

            List<StoreNotification> stores = storeRepo.findAllByDeskLocation(notification.getDeskLocation());
            StoreNotification store;

            if (stores != null && !stores.isEmpty()) {
                store = stores.get(0); // varsa ilk kaydı kullan
            } else {
                store = new StoreNotification();
                store.setStoreLocation(notification.getStoreLocation());
                store.setDeskLocation(notification.getDeskLocation());
                store = storeRepo.save(store);
            }

            notification.setStore(store);


            notification.setStore(store);
            Notification saved = notificationRepo.save(notification);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Sunucu hatası: " + e.getMessage());
        }
    }

    


}