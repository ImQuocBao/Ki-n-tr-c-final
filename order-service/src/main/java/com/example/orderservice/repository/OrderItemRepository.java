package com.example.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.orderservice.model.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{

	@Query("select ot from OrderItem ot where ot.orderId = ?1")
	List<OrderItem> findOrderItemByOrderID(Integer orderId);


	@Query("select ot from OrderItem ot where ot.orderId = ?2 and ot.bookId = ?1 ")
	List<OrderItem> isDuplicate(Integer bookId, Integer id);
	
}
