package com.example.orderservice.controller;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.controller.dto.OrderDto;
import com.example.orderservice.controller.dto.OrderItemDto;
import com.example.orderservice.exception.BookNotFoundException;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.service.OrderItemService;
import com.example.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final OrderItemService orderItemService;
	private final OrderItemRepository itemRepository;

	@GetMapping("/order")
	@Cacheable(value = "dto", key = "#id")
	public ResponseEntity<?> getOrderByCustomer(@RequestParam("cus_id") Integer id) {
		System.out.println("Call Again");
		OrderDto dto = orderService.getOrderByUserId(id);
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/order-item")
	public ResponseEntity<?> addOrderItem(@RequestParam("order_id") Integer id, @RequestBody OrderItemDto dto) {
		if (orderItemService.isBook(dto.getBookId())) {
			throw new BookNotFoundException("Book id not found: " + " " + dto.getBookId());
		}
		List<OrderItem> items = itemRepository.isDuplicate(dto.getBookId(), id);
		if (items.size() > 0) {
			return ResponseEntity.ok(orderItemService.updateQuantity(dto, id));
		}
		return ResponseEntity.ok(orderItemService.save(dto, id));
	}
}
