package com.libraryManagement.controller;

import com.libraryManagement.dto.PatronDTO;
import com.libraryManagement.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @PostMapping
    public ResponseEntity<PatronDTO> createPatron(@RequestBody PatronDTO patronDTO) {
        return ResponseEntity.ok(patronService.savePatron(patronDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Long id) {
        return ResponseEntity.ok(patronService.getPatronById(id));
    }

    @GetMapping
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        return ResponseEntity.ok(patronService.getAllPatrons());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id, @RequestBody PatronDTO patronDTO) {
        PatronDTO updatedPatron = patronService.updatePatron(id, patronDTO);
        return updatedPatron != null ? ResponseEntity.ok(updatedPatron) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
