package com.blit.ecommerce.project.repository;

import com.blit.ecommerce.project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
