package com.example.librarycatalog.repository;

import com.example.librarycatalog.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
