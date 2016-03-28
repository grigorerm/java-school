package com.db.javaschool.finalproject.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@javax.persistence.Entity
public class Supplier implements Entity {

    @Id
    @GeneratedValue
    private Long id_supplier;

    @Column(unique = true, length = 16, nullable = false)
    private String name_supplier;

    protected Supplier() {
		/* Reflection instantiation */

    }

    public Supplier(String name) {
        this.name_supplier = name;
    }

    public Long getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(Long id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getName_supplier() {
        return name_supplier;
    }

    public void setName_supplier(String name_supplier) {
        this.name_supplier = name_supplier;
    }
}