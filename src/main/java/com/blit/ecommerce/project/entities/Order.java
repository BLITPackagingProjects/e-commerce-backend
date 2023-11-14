package com.blit.ecommerce.project.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orderdetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;

    @Column
    private Date date;

    @OneToMany(mappedBy = "order")
    private List<Product> productList;

    @ManyToOne
    private User user;

}
