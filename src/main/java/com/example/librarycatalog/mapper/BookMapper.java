package com.example.librarycatalog.mapper;

import com.example.librarycatalog.dto.BookRequest;
import com.example.librarycatalog.dto.BookResponse;
import com.example.librarycatalog.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequest request) {
        if (request == null) return null;
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPublishedYear(request.getPublishedYear());
        return book;
    }

    public BookResponse toResponse(Book book) {
        if (book == null) return null;
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setIsbn(book.getIsbn());
        response.setPublishedYear(book.getPublishedYear());
        if (book.getAuthor() != null) {
            response.setAuthorName(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
        }
        return response;
    }
}
