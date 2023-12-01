package com.blit.ecommerce.project.repository;

import com.blit.ecommerce.project.entities.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderDetail, Long> {

	@Query(nativeQuery = true, value = "select order_detail.* from order_detail WHERE user_id= ?1")
	List<OrderDetail> findOrderByUserId(Long user_id);

	@Query(nativeQuery = true, value = "select order_detail.* from order_detail left join order_products on order_detail.order_id=order_products.order_id where product_id = ?1")
	List<OrderDetail> findOrdersByProductId(Long product_id);
	
	@Query(nativeQuery = true, value = "select order_detail.* from order_detail WHERE user_id= ?1 AND canceled=0 order by date DESC LIMIT 1")
	OrderDetail findActiveOrderByUserId(Long userId);
}
