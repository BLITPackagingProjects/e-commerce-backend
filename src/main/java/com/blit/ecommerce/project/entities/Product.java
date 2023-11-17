package com.blit.ecommerce.project.entities;

import com.blit.ecommerce.project.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(long product_id, String name, double price) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
    }

    public Product(long product_id, String name, int quantity) {
        this.product_id = product_id;
        this.name = name;
        this.quantity = quantity;
    }

    public Product(long product_id, String name, double price, String imageName, String description, String seller, Integer quantity) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
        this.seller = seller;
        this.quantity = quantity;
    }

    public void addStock(Integer qty){
        this.quantity += qty;
    }

    public void removeStock(Integer qty){
        if (this.quantity >= qty) {
            this.quantity -= qty;
        } else {
            throw new NotEnoughStockException("Not enough stock available.");
        }
    }


}
