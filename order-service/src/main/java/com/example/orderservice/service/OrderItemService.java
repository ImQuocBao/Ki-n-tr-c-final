package com.example.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.orderservice.controller.dto.OrderItemDto;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {

	private final RestTemplate restTemplate;
	private final OrderItemRepository itemRepository;

	public OrderItem save(OrderItemDto dto,Integer orderId) {
		OrderItem orderItem = new OrderItem();
		orderItem.setBookId(dto.getBookId());
		orderItem.setBookName(dto.getBookName());
		orderItem.setPrice(dto.getPrice());
		orderItem.setQuatity(dto.getQuatity());
		orderItem.setOrderId(orderId);
		return itemRepository.save(orderItem);
	}

	public List<OrderItem> getOrderByOrderId(Integer id) {
		return itemRepository.findOrderItemByOrderID(id);
	}

	public Object updateQuantity(OrderItemDto dto, Integer id) {
		OrderItem orderItem = itemRepository.isDuplicate(dto.getBookId(),id).stream().findFirst().get();
		orderItem.setQuatity(orderItem.getQuatity()+dto.getQuatity());
		return itemRepository.save(orderItem);
	}
	
	public boolean isBook(Integer id) {
		Boolean isbook = restTemplate.getForObject("http://localhost:9001/api/v1/isBook?book_id=1", Boolean.class);
		return isbook;
	}
}
