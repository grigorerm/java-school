package com.db.javaschool.finalproject.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@javax.persistence.Entity
public class RowCommand implements Entity {

    @Id
    @GeneratedValue
    private Long index_row_command;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity_ordered;

    @Column(length = 16, nullable = false)
    private long id_product;


    protected RowCommand() {
		/* Reflection instantiation */

    }

    public RowCommand(double price, int quantity, long id_product) {
        this.price = price;
        this.quantity_ordered = quantity;
        this.id_product = id_product;
    }

    public Long getIndex_row_command() {
        return index_row_command;
    }

    public void setIndex_row_command(Long index_row_command) {
        this.index_row_command = index_row_command;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity_ordered ;
    }

    public void setQuantity(int quantity) {
        this.quantity_ordered = quantity;
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }
}