package com.blit.ecommerce.project.entities;

import com.blit.ecommerce.project.exception.NotEnoughStockException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import org.springframework.context.annotation.Bean;

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

	@Column
    private String name;

    @Column
    private String status;

    @Column
    private double price;

    @Column
    private String imagename;

    @Column
    private String description;

    @Column
    private String seller;

    @Column
    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

}
