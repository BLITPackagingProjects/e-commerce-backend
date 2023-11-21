package com.blit.ecommerce.project.entities;

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
    
    
    public Product(String name, double price, String imageName, String description, String seller, Long quantity,
			 Order order) {
		super();
		this.name = name;
		this.price = price;
		this.imageName = imageName;
		this.description = description;
		this.seller = seller;
		this.quantity = quantity;

		this.order = order;
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
    private Long quantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    private String status;


    public Product(String name, double price, String imageName, String description, String seller, String status) {
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
        this.seller = seller;
        this.status = status;
    }
}
