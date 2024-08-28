package com.libraryManagement.service;

import com.libraryManagement.dto.BorrowingRecordDTO;

import java.util.List;

public interface BorrowingRecordService {
    BorrowingRecordDTO saveBorrowingRecord(BorrowingRecordDTO borrowingRecordDTO);
    BorrowingRecordDTO getBorrowingRecordById(Long id);
    List<BorrowingRecordDTO> getAllBorrowingRecords();
    void deleteBorrowingRecord(Long id);

    BorrowingRecordDTO updateBorrowingRecord(Long id, BorrowingRecordDTO borrowingRecordDTO);
}
