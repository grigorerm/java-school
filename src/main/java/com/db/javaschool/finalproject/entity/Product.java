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

    @Column(length = 16, nullable = false)
    private String product_name;
    
    @Column(length = 16, nullable = false)
    private String supplier_name;
    
    @Column(nullable = false)
    private double price;

	@Column(nullable = false)
    private int quantity;


	protected Product() {
		/* Reflection instantiation */
    }

    public Product(String name, String supplier, double price, int quantity) {
        this.product_name = name;
        this.supplier_name = supplier;
        this.price = price;
        this.quantity = quantity;
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
    public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	
    public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}