package com.example.librarycatalog.controller;

import com.example.librarycatalog.dto.BookRequest;
import com.example.librarycatalog.dto.BookResponse;
import com.example.librarycatalog.entity.Book;
import com.example.librarycatalog.mapper.BookMapper;
import com.example.librarycatalog.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        Book book = bookMapper.toEntity(request);
        Book created = bookService.createBook(request.getAuthorId(), book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(bookMapper.toResponse(book));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> responses = bookService.getAllBooks().stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookResponse>> getBooksByAuthor(@PathVariable Long authorId) {
        List<BookResponse> responses = bookService.getBooksByAuthor(authorId).stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Book updated = bookService.updateBook(id, bookMapper.toEntity(request));
        return ResponseEntity.ok(bookMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
