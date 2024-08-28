package com.libraryManagement.service.impl;

import com.libraryManagement.dto.PatronDTO;
import com.libraryManagement.entity.Patron;
import com.libraryManagement.repository.PatronRepository;
import com.libraryManagement.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public PatronDTO savePatron(PatronDTO patronDTO) {
        Patron patron = new Patron();
        patron.setName(patronDTO.getName());
        patron.setContactInfo(patronDTO.getContactInfo());
        patron.setEmail(patronDTO.getEmail());
        patron = patronRepository.save(patron);
        return convertToDTO(patron);
    }

    @Override
    public PatronDTO getPatronById(Long id) {
        return patronRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public List<PatronDTO> getAllPatrons() {
        return patronRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatronDTO updatePatron(Long id, PatronDTO patronDTO) {
        Optional<Patron> existingPatron = patronRepository.findById(id);
        if (existingPatron.isPresent()) {
            Patron patron = existingPatron.get();
            patron.setName(patronDTO.getName());
            patron.setContactInfo(patronDTO.getContactInfo());
            patron.setEmail(patronDTO.getEmail());
            patron = patronRepository.save(patron);
            return convertToDTO(patron);
        }
        return null;
    }

    @Override
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }

    private PatronDTO convertToDTO(Patron patron) {
        PatronDTO dto = new PatronDTO();
        dto.setId(patron.getId());
        dto.setName(patron.getName());
        dto.setContactInfo(patron.getContactInfo());
        dto.setEmail(patron.getEmail());
        return dto;
    }
}
