package com.blit.ecommerce.project.repository;

import com.blit.ecommerce.project.entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
