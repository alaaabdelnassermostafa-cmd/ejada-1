package com.example.librarycatalog.controller;

import com.example.librarycatalog.dto.AuthorRequest;
import com.example.librarycatalog.dto.AuthorResponse;
import com.example.librarycatalog.entity.Author;
import com.example.librarycatalog.mapper.AuthorMapper;
import com.example.librarycatalog.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest request) {
        Author author = authorMapper.toEntity(request);
        Author created = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorMapper.toResponse(author));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        List<AuthorResponse> responses = authorService.getAllAuthors().stream()
                .map(authorMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequest request) {
        Author updated = authorService.updateAuthor(id, authorMapper.toEntity(request));
        return ResponseEntity.ok(authorMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
