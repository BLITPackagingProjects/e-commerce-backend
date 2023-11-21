package com.blit.ecommerce.project.repository;

import com.blit.ecommerce.project.entities.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderDetail, Long> {

}
