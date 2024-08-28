package com.libraryManagement.mapper;

import com.libraryManagement.dto.BorrowingRecordDTO;
import com.libraryManagement.entity.BorrowingRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowingRecordMapper {
    BorrowingRecordDTO toDTO(BorrowingRecord borrowingRecord);
    BorrowingRecord toEntity(BorrowingRecordDTO borrowingRecordDTO);
}
