package com.example.librarycatalog.mapper;

import com.example.librarycatalog.dto.AuthorRequest;
import com.example.librarycatalog.dto.AuthorResponse;
import com.example.librarycatalog.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequest request) {
        if (request == null) return null;
        return new Author(request.getFirstName(), request.getLastName());
    }

    public AuthorResponse toResponse(Author author) {
        if (author == null) return null;
        int bookCount = author.getBooks() != null ? author.getBooks().size() : 0;
        return new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName(), bookCount);
    }
}
