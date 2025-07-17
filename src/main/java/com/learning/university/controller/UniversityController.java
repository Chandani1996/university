package com.learning.university.controller;

import com.learning.university.model.UniversityDTO;
import com.learning.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveUniversityDetails(@RequestBody UniversityDTO universityDTO) {
        try {
            UniversityDTO universityDetails = universityService.saveUniversityDetails(universityDTO);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(universityDetails);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/university/{id}")
    public ResponseEntity<Object> displayUniversityStudentDetails(@PathVariable Integer id) {
        try {
            List<String> universityNameList = universityService.fetchUniversityStudentnames(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(universityNameList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
