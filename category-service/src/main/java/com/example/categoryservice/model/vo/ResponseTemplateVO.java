package com.example.categoryservice.model.vo;


import com.example.categoryservice.model.Category;
import lombok.Data;

@Data
public class ResponseTemplateVO {

	private Category category;

	private Book[] books;
}
