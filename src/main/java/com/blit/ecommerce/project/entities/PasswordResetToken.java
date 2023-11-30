package com.blit.ecommerce.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch= FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    private Date expiryDate;
    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
