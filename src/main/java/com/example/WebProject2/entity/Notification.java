package com.example.WebProject2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Notification {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String service;
    private String name;
    private String phone;
    private String mail;
    private String storeLocation;
    private String description;
    private int deskLocation;
    private int serialNo;

    @ManyToOne(optional = true)
    @JoinColumn(name="store_id")
    private StoreNotification store;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeskLocation() {
        return deskLocation;
    }

    public void setDeskLocation(int deskLocation) {
        this.deskLocation = deskLocation;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public StoreNotification getStore() {
        return store;
    }

    public void setStore(StoreNotification store) {
        this.store = store;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }


}
