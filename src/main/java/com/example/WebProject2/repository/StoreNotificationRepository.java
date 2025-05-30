package com.example.WebProject2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebProject2.entity.StoreNotification;

public interface StoreNotificationRepository extends JpaRepository<StoreNotification, Long> {
    List<StoreNotification> findAllByDeskLocation(int deskLocation);

    List<StoreNotification> findAllByOrderByDeskLocationAsc();
}
