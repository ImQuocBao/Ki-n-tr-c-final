package com.example.orderservice.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String bookName;
	private Integer bookId;
	private Double price;
	private Integer quatity;
	private Integer orderId;
}
