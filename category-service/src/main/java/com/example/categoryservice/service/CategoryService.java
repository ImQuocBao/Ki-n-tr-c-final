package com.example.categoryservice.service;

import com.example.categoryservice.model.Category;
import com.example.categoryservice.model.vo.Book;
import com.example.categoryservice.model.vo.ResponseTemplateVO;
import com.example.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final RestTemplate restTemplate;

	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	public ResponseTemplateVO getCategoryById(Integer id) {
		Category category = categoryRepository.findById(id).get();

		Book[] books = restTemplate
				.getForObject("http://localhost:9001/api/v1/book?cate_id=" + category.getId(), Book[].class);
		ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
		responseTemplateVO.setBooks(books);
		responseTemplateVO.setCategory(category);
		return responseTemplateVO;
	}

	@Transactional(readOnly = true)
	public Category findById(int id){
		return categoryRepository.findById(id).orElse(null);
	}
}
