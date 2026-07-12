package com.example.librarycatalog.dto;

public class AuthorResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private int bookCount;

    public AuthorResponse() {}

    public AuthorResponse(Long id, String firstName, String lastName, int bookCount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookCount = bookCount;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getBookCount() { return bookCount; }
    public void setBookCount(int bookCount) { this.bookCount = bookCount; }
}
