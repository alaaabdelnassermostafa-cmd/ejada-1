package com.example.librarycatalog.repository;

import com.example.librarycatalog.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);
    boolean existsByIsbn(String isbn);
}
