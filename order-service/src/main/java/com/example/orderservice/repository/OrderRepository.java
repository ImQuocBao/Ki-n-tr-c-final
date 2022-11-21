package com.example.orderservice.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.orderservice.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{

	@Query("Select o from Order o where o.userId = ?1 and o.statusOrder = true")
	Order getOrderByUserId(Integer id);


}
