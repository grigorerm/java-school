package com.db.javaschool.finalproject.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity
public class Product implements Entity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id_product;

    @Column(unique = true, length = 16, nullable = false)
    private String product_name;

    protected Product() {
		/* Reflection instantiation */
    }

    public Product(String name) {
        this.product_name = name;
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