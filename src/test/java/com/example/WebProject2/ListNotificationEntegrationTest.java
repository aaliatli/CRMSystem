package com.example.WebProject2;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.WebProject2.entity.Notification;
import com.example.WebProject2.entity.StoreNotification;
import com.example.WebProject2.repository.NotificationRepository;
import com.example.WebProject2.repository.StoreNotificationRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ListNotificationEntegrationTest {

    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    private StoreNotificationRepository storeRepo;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void clearDb(){
        notificationRepo.deleteAll();
        storeRepo.deleteAll();
    }

    @Test
    public void testAllAnalaysis() throws Exception{
        Notification notification = new Notification();
        notification.setName("Ali Ali");
        notification.setService("Tekerlekli Sandalye");
        notification.setDescription("description");
        notification.setMail("mail@mail");
        notification.setStoreLocation("storeLocation");
        notification.setDeskLocation(1);
        notification.setPhone("05555555555");
        notification.setSerialNo(123);
        notificationRepo.save(notification);

        StoreNotification store = new StoreNotification();
        store.setDeskLocation(notification.getDeskLocation());
        store.setStoreLocation(notification.getStoreLocation());
        storeRepo.save(store);
        
        assertEquals(1, storeRepo.count());
        assertEquals(1, storeRepo.count());
        
    }

    @Test
    public void testDeleteNotifications() throws Exception{
        Notification notification = new Notification();
        notification.setName("Ali Ali");
        notification.setService("Tekerlekli Sandalye");
        notification.setDescription("description");
        notification.setMail("mail@mail");
        notification.setStoreLocation("storeLocation");
        notification.setDeskLocation(1);
        notification.setPhone("05555555555");
        notification.setSerialNo(123);
        notificationRepo.save(notification);

        Long id = notification.getId();

        mockMvc.perform(delete("/api/notifications/" + id)).andExpect(status().isOk());

        assertEquals(0, notificationRepo.count());
    }
    
}
