package com.blit.ecommerce.project.entities;

import com.blit.ecommerce.project.exception.NotEnoughStockException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;

// make repo/ service/and controller for product and work on it Repository
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long product_id;

    public Product(String name, double price, String imageName, String description, String seller, int quantity
            ) {
        super();
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
        this.seller = seller;
        this.quantity = quantity;

        
    }

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private String imageName;

    @Column
    private String description;

    @Column
    private String seller;

    @Column
    private int quantity;



    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void addStock(int qty) {
        this.quantity += qty;
    }

    public void removeStock(int qty) {
        if (this.quantity >= qty) {
            this.quantity -= qty;
        } else {
            throw new NotEnoughStockException("Not enough stock available.");
        }
    }

}
