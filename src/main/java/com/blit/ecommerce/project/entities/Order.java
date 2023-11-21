package com.blit.ecommerce.project.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Product> productList;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
