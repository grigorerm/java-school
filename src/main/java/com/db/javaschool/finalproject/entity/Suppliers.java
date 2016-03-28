package com.db.javaschool.finalproject.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@javax.persistence.Entity
public class Suppliers implements Entity {

    @Id
    @GeneratedValue
    private Long id_supplier;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity_available;

    @Column(length = 16, nullable = false)
    private long id_product;


    protected Suppliers() {
		/* Reflection instantiation */

    }

    public Suppliers(double price, int quantity, long id_product) {
        this.price = price;
        this.quantity_available = quantity;
        this.id_product = id_product;
    }

    public Long getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(Long id_supplier) {
        this.id_supplier = id_supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity_available() {
        return quantity_available;
    }

    public void setQuantity_available(int quantity_available) {
        this.quantity_available = quantity_available;
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }
}