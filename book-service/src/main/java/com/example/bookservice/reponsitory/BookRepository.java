package com.example.bookservice.reponsitory;

import com.example.bookservice.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("select c from Book c where c.cateId = ?1")
    List<Book> findByCateId(Integer id);

    List<Book> findByNameContaining(String nameSearch);
}
