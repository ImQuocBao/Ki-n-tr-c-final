package com.example.bookservice.service;

import com.example.bookservice.entity.Book;
import com.example.bookservice.reponsitory.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBookByCategoryId(Integer id){
        return bookRepository.findByCateId(id);
    }
}