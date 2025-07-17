package com.learning.university.controller;

import com.learning.university.model.AddStudentDetailsRequest;
import com.learning.university.model.UniversityDTO;
import com.learning.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public UniversityDTO saveUniversityDetails(@RequestBody UniversityDTO universityDTO){
        return universityService.saveUniversityDetails(universityDTO);
    }
    @PutMapping("/add")
    public AddStudentDetailsRequest addStudentDetails(@RequestBody AddStudentDetailsRequest addStudentDetails){
        return universityService.addStudentDetails(addStudentDetails);
    }
}
