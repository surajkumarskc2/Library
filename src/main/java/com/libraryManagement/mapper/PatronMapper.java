package com.libraryManagement.mapper;

import com.libraryManagement.dto.PatronDTO;
import com.libraryManagement.entity.Patron;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatronMapper {
    PatronDTO toDTO(Patron patron);
    Patron toEntity(PatronDTO patronDTO);
}
