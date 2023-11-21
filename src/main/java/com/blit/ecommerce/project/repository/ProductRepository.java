package com.blit.ecommerce.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blit.ecommerce.project.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery=true, value="select * FROM Product WHERE name REGEXP ?1")
    List<Product> findProductByNameRegex(String regex);

}