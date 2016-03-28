package com.db.javaschool.finalproject.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@javax.persistence.Entity
public class Command implements Entity {

    @Id
    @GeneratedValue
    private Long id_command;

    @Column(nullable = false)
    private Date date_delivery;

    @Column(length = 16, nullable = false)
    private long id_user;

    @Column(length = 16, nullable = false)
    private long index_row_command;

    protected Command() {
		/* Reflection instantiation */

    }

    public Command(Date date, long id_user, long id_command) {
        this.date_delivery = date;
        this.id_user = id_user;
        this.id_command = id_command;
    }

    public Long getId_command() {
        return id_command;
    }

    public void setId_command(Long id_command) {
        this.id_command = id_command;
    }

    public Date getDate_delivery() {
        return date_delivery;
    }

    public void setDate_delivery(Date date_delivery) {
        this.date_delivery = date_delivery;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public long getIndex_row_command() {
        return index_row_command;
    }

    public void setIndex_row_command(long index_row_command) {
        this.index_row_command = index_row_command;
    }

}