package com.libraryManagement.mapper;

import com.libraryManagement.dto.BookDTO;
import com.libraryManagement.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(Book book);
    Book toEntity(BookDTO bookDTO);
}
