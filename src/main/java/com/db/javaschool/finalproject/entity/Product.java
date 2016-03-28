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
public class Product implements Entity {

    @Id
    @GeneratedValue
    private Long id_product;

    @Column(unique = true, length = 16, nullable = false)
    private String product_name;

    protected Product() {
		/* Reflection instantiation */
    }

    public Product(String name) {
        this.product_name = product_name;
    }

    public Long getId() {
        return this.id_product;
    }

    public void setId(Long id) {
        this.id_product = id;
    }

    public String getName() {
        return this.product_name;
    }

    public void setName(String name) {
        this.product_name = name;
    }

}