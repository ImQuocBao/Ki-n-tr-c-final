package com.example.orderservice.controller.dto;

import java.util.List;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;

import lombok.Data;

@Data
public class OrderDto {

	private Integer id;

	private Integer userId;

	private String userEmail;

	private String userAddress;

	private boolean statusOrder; 
	List<OrderItem> orderItems;
}
