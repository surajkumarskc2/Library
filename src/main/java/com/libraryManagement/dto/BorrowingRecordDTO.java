package com.libraryManagement.dto;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BorrowingRecordDTO {
    private Long id;

    @NotNull(message = "Book ID must not be null")
    private Long bookId;

    @NotNull(message = "Patron ID must not be null")
    private Long patronId;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    // Getters and Setters

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getPatronId() {
        return patronId;
    }

    public void setPatronId(Long patronId) {
        this.patronId = patronId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
