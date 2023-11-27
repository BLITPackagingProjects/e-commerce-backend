package com.blit.ecommerce.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blit.ecommerce.project.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "select * FROM Product WHERE name REGEXP ?1")
	List<Product> findProductByNameRegex(String regex);
	
	//findProductByOrderDetail_OrderId() //this would work if orderId were named like that, instead of order_id
	//underscore is a special character, so with when a property has it in a name, gotta do the query manually
	@Query(nativeQuery = true, value = "select Product.* from Product "
			+ "LEFT JOIN order_products ON product.product_id=order_products.product_id "
//			+ "LEFT JOIN order_detail ON order_detail.order_id=order_products.order_id "
			+ "WHERE order_id= ?1")
	List<Product> findProductByOrderId(Long order_id);

}
