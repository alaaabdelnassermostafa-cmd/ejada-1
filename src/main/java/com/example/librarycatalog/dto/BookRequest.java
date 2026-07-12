package com.example.librarycatalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class BookRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^[0-9]{10}([0-9]{3})?$", message = "ISBN must be 10 or 13 digits")
    private String isbn;

    @NotNull(message = "Published year is required")
    @Min(value = 1000, message = "Published year must be at least 1000")
    @Max(value = 2100, message = "Published year cannot exceed 2100")
    private Integer publishedYear;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    public BookRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
}
