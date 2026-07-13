package com.example.librarycatalog.service;

import com.example.librarycatalog.entity.Author;
import com.example.librarycatalog.exception.DuplicateResourceException;
import com.example.librarycatalog.exception.ResourceNotFoundException;
import com.example.librarycatalog.kafka.LibraryEventProducer;
import com.example.librarycatalog.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final LibraryEventProducer eventProducer;

    public AuthorService(AuthorRepository authorRepository, LibraryEventProducer eventProducer) {
        this.authorRepository = authorRepository;
        this.eventProducer    = eventProducer;
    }

    public Author createAuthor(Author author) {
        if (authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName())) {
            throw new DuplicateResourceException("Author with name '" + author.getFirstName() + " " + author.getLastName() + "' already exists");
        }
        Author saved = authorRepository.save(author);
        eventProducer.publishAuthorEvent("CREATED", saved.getId(),
                "Author created: " + saved.getFirstName() + " " + saved.getLastName());
        return saved;
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthor(Long id, Author updated) {
        Author existing = getAuthorById(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        Author saved = authorRepository.save(existing);
        eventProducer.publishAuthorEvent("UPDATED", saved.getId(),
                "Author updated: " + saved.getFirstName() + " " + saved.getLastName());
        return saved;
    }

    public void deleteAuthor(Long id) {
        Author existing = getAuthorById(id);
        authorRepository.delete(existing);
        eventProducer.publishAuthorEvent("DELETED", id,
                "Author deleted with id: " + id);
    }
}
