package com.libraryManagement.service;

import com.libraryManagement.dto.BookDTO;

import java.util.List;

public interface BookService {
//    BookDTO saveBook(BookDTO bookDTO);
    BookDTO getBookById(Long id);
    List<BookDTO> getAllBooks();
    void deleteBook(Long id);
    BookDTO updateBook(Long id, BookDTO bookDTO);

    BookDTO saveBook(BookDTO bookDTO);
}
