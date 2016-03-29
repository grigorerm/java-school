package com.db.javaschool.finalproject.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

import antlr.collections.List;

@javax.persistence.Entity
public class Store implements Entity {

    @Id
    @GeneratedValue
    private Long id_store;

    @Column(unique = true, length = 16, nullable = false)
    private String name;


    @Column(unique = true, length = 16, nullable = false)
    private String address;

    //@OneToMany( mappedBy="store")
    private ArrayList<User> users;
    
    protected Store() {
		/* Reflection instantiation */
    }

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return this.id_store;
    }

    public void setId(Long id) {
        this.id_store = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    }