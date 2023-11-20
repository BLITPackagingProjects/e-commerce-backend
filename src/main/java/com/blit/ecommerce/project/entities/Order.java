package com.blit.ecommerce.project.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime date;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private boolean canceled;

    public void setCanceled(boolean canceled){
        this.canceled = canceled;
    }

}
