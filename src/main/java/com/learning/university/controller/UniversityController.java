package com.learning.university.controller;

import com.learning.university.model.UniversityDTO;
import com.learning.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public UniversityDTO saveUniversityDetails(@RequestBody UniversityDTO universityDTO){
        return universityService.saveUniversityDetails(universityDTO);
    }
}
