package com.libraryManagement.repository;

import com.libraryManagement.entity.Book;
import com.libraryManagement.entity.BorrowingRecord;
import com.libraryManagement.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookAndPatron(Book book, Patron patron);
}

