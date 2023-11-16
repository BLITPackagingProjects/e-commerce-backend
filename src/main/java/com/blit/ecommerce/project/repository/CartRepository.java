package com.blit.ecommerce.project.repository;

import com.blit.ecommerce.project.entities.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
}
