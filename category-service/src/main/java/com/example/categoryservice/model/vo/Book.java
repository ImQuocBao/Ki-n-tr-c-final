package com.example.categoryservice.model.vo;

import lombok.Data;

@Data
public class Book {

	private Integer id;
	private String name;
	private String author;
	private Double price;
	private String des;
	private Double rate;
	private String image;
	private Integer amount;
	private String tag;
	private Integer cateId;
}
