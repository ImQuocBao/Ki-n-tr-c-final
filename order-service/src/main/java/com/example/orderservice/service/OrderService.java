package com.example.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orderservice.controller.dto.OrderDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	private final OrderItemService orderItemService;


	public OrderDto getOrderByUserId(Integer id) {
		Order order = orderRepository.getOrderByUserId(id);
		List<OrderItem> orderItems = orderItemService.getOrderByOrderId(order.getId());
		OrderDto dto = new OrderDto();
		dto.setId(order.getId());
		dto.setStatusOrder(order.isStatusOrder());
		dto.setUserAddress(order.getUserAddress());
		dto.setUserEmail(order.getUserEmail());
		dto.setUserId(order.getUserId());
		dto.setOrderItems(orderItems);
		return dto;
	}
	
}
