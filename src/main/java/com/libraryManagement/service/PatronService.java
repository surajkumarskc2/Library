package com.libraryManagement.service;

import com.libraryManagement.dto.PatronDTO;

import java.util.List;

public interface PatronService {
    PatronDTO savePatron(PatronDTO patronDTO);
    PatronDTO getPatronById(Long id);
    List<PatronDTO> getAllPatrons();
    PatronDTO updatePatron(Long id, PatronDTO patronDTO);  // New method
    void deletePatron(Long id);
}
