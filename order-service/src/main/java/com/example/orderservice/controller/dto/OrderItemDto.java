package com.example.orderservice.controller.dto;

import lombok.Data;

@Data
public class OrderItemDto{

	private String bookName;
	private Integer bookId;
	private Double price;
	private Integer quatity;
}
