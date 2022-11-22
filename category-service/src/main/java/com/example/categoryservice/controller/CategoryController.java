package com.example.categoryservice.controller;

import com.example.categoryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category-service")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("findById")
    public ResponseEntity findById(@RequestParam("id") int id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }
}
