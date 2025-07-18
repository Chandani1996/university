package com.learning.university.controller;

import com.learning.university.entity.University;
import com.learning.university.model.UniversityDTO;
import com.learning.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public UniversityDTO saveUniversityDetails(@RequestBody UniversityDTO universityDTO) {
        return universityService.saveUniversityDetails(universityDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> returnUniversityDetails(@RequestParam(value = "university_location", required = true) String location,
                                                                 @RequestParam(value = "university_year", required = true) String establishedYear) {
        try {
            Map<String, String> returnUniversityList = universityService
                    .returnUniversityDetails(location,establishedYear);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(returnUniversityList);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status((HttpStatus.BAD_REQUEST))
                    .body(e.getMessage());
        }
    }
}
