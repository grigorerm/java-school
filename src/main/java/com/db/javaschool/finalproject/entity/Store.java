package com.db.javaschool.finalproject.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@javax.persistence.Entity
public class Store implements Entity {

    @Id
    @GeneratedValue
    private Long id_store;

    @Column(unique = true, length = 16, nullable = false)
    private String name;


    @Column(unique = true, length = 16, nullable = false)
    private String address;


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