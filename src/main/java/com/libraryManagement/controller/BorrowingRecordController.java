package com.libraryManagement.controller;

import com.libraryManagement.dto.BorrowingRecordDTO;
import com.libraryManagement.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    @Autowired
    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping
    public ResponseEntity<BorrowingRecordDTO> createBorrowingRecord(@Valid @RequestBody BorrowingRecordDTO borrowingRecordDTO) {
        if (borrowingRecordDTO.getBookId() == null || borrowingRecordDTO.getPatronId() == null) {
            return ResponseEntity.badRequest().body(null); // Handle invalid request
        }
        return ResponseEntity.ok(borrowingRecordService.saveBorrowingRecord(borrowingRecordDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingRecordDTO> updateBorrowingRecord(@PathVariable Long id, @Valid @RequestBody BorrowingRecordDTO borrowingRecordDTO) {
        if (borrowingRecordDTO.getBookId() == null || borrowingRecordDTO.getPatronId() == null) {
            return ResponseEntity.badRequest().body(null); // Handle invalid request
        }
        return ResponseEntity.ok(borrowingRecordService.updateBorrowingRecord(id, borrowingRecordDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecordDTO> getBorrowingRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingRecordService.getBorrowingRecordById(id));
    }

    @GetMapping
    public ResponseEntity<List<BorrowingRecordDTO>> getAllBorrowingRecords() {
        return ResponseEntity.ok(borrowingRecordService.getAllBorrowingRecords());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingRecordService.deleteBorrowingRecord(id);
        return ResponseEntity.noContent().build();
    }
}
