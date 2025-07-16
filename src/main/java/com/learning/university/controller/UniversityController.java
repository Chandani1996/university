package com.learning.university.controller;

import com.learning.university.model.StudentDTO;
import com.learning.university.model.UniversityDTO;
import com.learning.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public UniversityDTO saveUniversityDetails(@RequestBody UniversityDTO universityDTO) {
        return universityService.saveUniversityDetails(universityDTO);
    }

    @GetMapping("/university/{id}")
    public List<String> displayUniversityStudentDetails(@PathVariable Integer id) {
        return universityService.displayUniversityStudentDetails(id);
    }

}
