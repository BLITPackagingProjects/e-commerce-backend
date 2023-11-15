package com.blit.ecommerce.project.entities;

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
    private String status;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Order order;

    public Product(long product_id, String name, double price, String imageName, String description, String seller, String status) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
        this.seller = seller;
        this.status = status;
    }
}
