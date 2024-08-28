package com.libraryManagement.service.impl;

import com.libraryManagement.dto.BorrowingRecordDTO;
import com.libraryManagement.entity.BorrowingRecord;
import com.libraryManagement.entity.Book;
import com.libraryManagement.entity.Patron;
import com.libraryManagement.exception.BookNotFoundException;
import com.libraryManagement.exception.PatronNotFoundException;
import com.libraryManagement.exception.ResourceNotFoundException;
import com.libraryManagement.repository.BorrowingRecordRepository;
import com.libraryManagement.repository.BookRepository;
import com.libraryManagement.repository.PatronRepository;
import com.libraryManagement.service.BorrowingRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    private static final Logger log = LoggerFactory.getLogger(BorrowingRecordServiceImpl.class);

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    @Autowired
    public BorrowingRecordServiceImpl(BorrowingRecordRepository borrowingRecordRepository,
                                      BookRepository bookRepository,
                                      PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Transactional
    @Override
    public BorrowingRecordDTO saveBorrowingRecord(BorrowingRecordDTO borrowingRecordDTO) {
        if (borrowingRecordDTO.getBookId() == null || borrowingRecordDTO.getPatronId() == null) {
            log.error("Book ID or Patron ID is null: {}", borrowingRecordDTO);
            throw new IllegalArgumentException("Book ID and Patron ID must not be null.");
        }

        // Validate and fetch associated entities
        Book book = bookRepository.findById(borrowingRecordDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book ID not found: " + borrowingRecordDTO.getBookId()));
        Patron patron = patronRepository.findById(borrowingRecordDTO.getPatronId())
                .orElseThrow(() -> new PatronNotFoundException("Patron ID not found: " + borrowingRecordDTO.getPatronId()));

        // Convert DTO to entity
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(borrowingRecordDTO.getBorrowDate());
        borrowingRecord.setReturnDate(borrowingRecordDTO.getReturnDate());

        // Save the borrowing record
        BorrowingRecord savedRecord = borrowingRecordRepository.save(borrowingRecord);

        // Convert the entity back to DTO and return it
        return convertToDTO(savedRecord);
    }

    @Override
    public BorrowingRecordDTO getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing Record not found with ID: " + id));
    }

    @Override
    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteBorrowingRecord(Long id) {
        if (!borrowingRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Borrowing Record not found with ID: " + id);
        }
        borrowingRecordRepository.deleteById(id);
    }

    @Transactional
    @Override
    public BorrowingRecordDTO updateBorrowingRecord(Long id, BorrowingRecordDTO borrowingRecordDTO) {
        // Find the existing record
        BorrowingRecord existingRecord = borrowingRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing Record not found with ID: " + id));

        // Validate and fetch associated entities
        Book book = bookRepository.findById(borrowingRecordDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book ID not found: " + borrowingRecordDTO.getBookId()));
        Patron patron = patronRepository.findById(borrowingRecordDTO.getPatronId())
                .orElseThrow(() -> new PatronNotFoundException("Patron ID not found: " + borrowingRecordDTO.getPatronId()));

        // Update the record
        existingRecord.setBook(book);
        existingRecord.setPatron(patron);
        existingRecord.setBorrowDate(borrowingRecordDTO.getBorrowDate());
        existingRecord.setReturnDate(borrowingRecordDTO.getReturnDate());

        // Save the updated record
        BorrowingRecord updatedRecord = borrowingRecordRepository.save(existingRecord);

        // Convert the entity back to DTO and return it
        return convertToDTO(updatedRecord);
    }

    private BorrowingRecordDTO convertToDTO(BorrowingRecord borrowingRecord) {
        if (borrowingRecord == null) {
            log.debug("BorrowingRecord entity is null");
            return null; // Or handle this case as needed
        }

        BorrowingRecordDTO dto = new BorrowingRecordDTO();
        dto.setId(borrowingRecord.getId());
        dto.setBorrowDate(borrowingRecord.getBorrowDate());
        dto.setReturnDate(borrowingRecord.getReturnDate());
        dto.setBookId(borrowingRecord.getBook().getId());
        dto.setPatronId(borrowingRecord.getPatron().getId());
        return dto;
    }
}
