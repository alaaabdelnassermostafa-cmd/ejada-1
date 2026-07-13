package com.example.librarycatalog.service;

import com.example.librarycatalog.entity.Author;
import com.example.librarycatalog.entity.Book;
import com.example.librarycatalog.exception.DuplicateResourceException;
import com.example.librarycatalog.exception.ResourceNotFoundException;
import com.example.librarycatalog.kafka.LibraryEventProducer;
import com.example.librarycatalog.repository.AuthorRepository;
import com.example.librarycatalog.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LibraryEventProducer eventProducer;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       LibraryEventProducer eventProducer) {
        this.bookRepository   = bookRepository;
        this.authorRepository = authorRepository;
        this.eventProducer    = eventProducer;
    }

    public Book createBook(Long authorId, Book book) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DuplicateResourceException("Book with ISBN '" + book.getIsbn() + "' already exists");
        }
        book.setAuthor(author);
        Book saved = bookRepository.save(book);
        eventProducer.publishBookEvent("CREATED", saved.getId(),
                "Book created: '" + saved.getTitle() + "' (ISBN: " + saved.getIsbn() + ")");
        return saved;
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public Book updateBook(Long id, Book updated) {
        Book existing = getBookById(id);
        existing.setTitle(updated.getTitle());

        if (!existing.getIsbn().equals(updated.getIsbn()) && bookRepository.existsByIsbn(updated.getIsbn())) {
            throw new DuplicateResourceException("Book with ISBN '" + updated.getIsbn() + "' already exists");
        }

        existing.setIsbn(updated.getIsbn());
        existing.setPublishedYear(updated.getPublishedYear());
        Book saved = bookRepository.save(existing);
        eventProducer.publishBookEvent("UPDATED", saved.getId(),
                "Book updated: '" + saved.getTitle() + "' (ISBN: " + saved.getIsbn() + ")");
        return saved;
    }

    public void deleteBook(Long id) {
        Book existing = getBookById(id);
        bookRepository.delete(existing);
        eventProducer.publishBookEvent("DELETED", id,
                "Book deleted with id: " + id);
    }
}
