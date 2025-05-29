package com.example.WebProject2;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.WebProject2.entity.Notification;
import com.example.WebProject2.repository.NotificationRepository;
import com.example.WebProject2.repository.StoreNotificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper; 



@SpringBootTest
@AutoConfigureMockMvc
public class AddNotificationIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    private StoreNotificationRepository storeRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void clearDb(){
        notificationRepo.deleteAll();
        storeRepo.deleteAll();
    }

    @Test
    public void testAddNotification() throws Exception{
        Notification notification= new Notification();
        notification.setService("Tekerlekli Sandalye");
        notification.setName("Ali");
        notification.setPhone("05522549875");
        notification.setMail("admin@admin.com");
        notification.setDeskLocation(1);
        notification.setStoreLocation("Adidas");
        notification.setDescription("Aciklama");

        String json = objectMapper.writeValueAsString(notification);
        
         mockMvc.perform(post("/api/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ali"))
                .andExpect(jsonPath("$.store.deskLocation").value(1));

        assertEquals(1, notificationRepo.count());

    }

    @Test
    public void testStoreNotificationOnly() throws Exception{
        Notification notification = new Notification();
        notification.setService("Mağaza Lokasyon");
        notification.setStoreLocation("Adidas");
        notification.setDeskLocation(1);

        String json = objectMapper.writeValueAsString(notification);

        mockMvc.perform(post("/api/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Mağaza lokasyon bildirimi kaydedildi.")));

        assertEquals(1, storeRepo.count());

    }
}
