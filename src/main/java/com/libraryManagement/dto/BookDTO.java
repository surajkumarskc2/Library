package com.libraryManagement.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookDTO {
    private Long id;

    @NotEmpty
    @Size(min = 10, message = "Book title should be at least 10 characters")

    private String title;
    @NotEmpty
    private String author;
    @NotEmpty
    private int publicationYear;
    @NotEmpty
    private String isbn;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
