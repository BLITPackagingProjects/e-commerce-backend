package com.blit.ecommerce.project.repository;

import com.blit.ecommerce.project.entities.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderDetail, Long> {

	@Query(nativeQuery = true, value = "select * from OrderDetail WHERE user_id= ?1")
	List<OrderDetail> findOrderByUserId(Long user_id);
}
