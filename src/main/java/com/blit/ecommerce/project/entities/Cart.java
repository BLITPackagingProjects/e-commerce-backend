package com.blit.ecommerce.project.entities;

import com.blit.ecommerce.project.exception.ProductNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cart_id;

    @Column
    private int number;


    @OneToMany(mappedBy = "cart")
    private List<Product> productList =new ArrayList<>();

    public Cart(int cart_id) {
        this.cart_id = cart_id;
    }

    public void addCount(Integer qty){
        this.number += qty;
    }

    public void removeCount(Integer qty){
        int nextCount = this.number - qty;
        if(nextCount < 0){
            throw new ProductNotFoundException("No more product to delete");
        }
    }
}
