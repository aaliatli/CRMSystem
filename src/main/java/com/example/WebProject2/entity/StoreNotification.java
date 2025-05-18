package com.example.WebProject2.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StoreNotification{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String storeLocation;
    private int deskLocation;

    public Long getId() {
        return id;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public int getDeskLocation() {
        return deskLocation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public void setDeskLocation(int deskLocation) {
        this.deskLocation = deskLocation;
    }
}