package com.blit.ecommerce.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;

    @Column
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="order_products",
            joinColumns=@JoinColumn(name="orderId",referencedColumnName="order_id"),
            inverseJoinColumns=@JoinColumn(name="productId",referencedColumnName="product_id"))
    @JsonIgnore
    private List<Product> productList=new ArrayList<Product>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean canceled;

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

}