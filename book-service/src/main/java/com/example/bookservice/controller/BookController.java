package com.example.bookservice.controller;

import com.example.bookservice.entity.Book;
import com.example.bookservice.reponsitory.BookRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @GetMapping("/book")
    public ResponseEntity<?> getBookByCategory(@RequestParam("cate_id") Integer id) {
        return ResponseEntity.ok(bookService.getBookByCategoryId(id));
    }

    @GetMapping("/book-search")
    public ResponseEntity<?> getBookSearch(@RequestParam("name-search") String nameSearch) {
        return ResponseEntity.ok(bookRepository.findByNameContaining(nameSearch));
    }

    @GetMapping("/books")
    public ResponseEntity<?> getBookByCategories() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("book_id") Integer id) {
        return ResponseEntity.ok(bookRepository.findById(id));
    }

    @GetMapping("/isBook")
    public boolean isBook(@RequestParam("book_id") Integer id) {

        Book book = bookRepository.findById(id).get();
        if (book != null) {
            return true;
        }
        return false;
    }
}