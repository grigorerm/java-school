package com.db.javaschool.finalproject.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.*;

@javax.persistence.Entity
public class History implements Entity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 16)
    private String user;

    @Column()
    private String date_delivery;

    @Column(length = 16 )
    private String store;

    @Column(length = 16 )
    private String address;

    @Column(length = 16)
    private String orderInfo;

    public History() {
    }

    public History(String user, String date_delivery, String store, String address, String orderInfo) {
        this.user = user;
        this.date_delivery = date_delivery;
        this.store = store;
        this.address = address;
        this.orderInfo = orderInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate_delivery() {
        return date_delivery;
    }

    public void setDate_delivery(String date_delivery) {
        this.date_delivery = date_delivery;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }
}
