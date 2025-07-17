package com.learning.university.controller;

import com.learning.university.entity.University;
import com.learning.university.model.UniversityDTO;
import com.learning.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public UniversityDTO saveUniversityDetails(@RequestBody UniversityDTO universityDTO){
        return universityService.saveUniversityDetails(universityDTO);
    }

    @GetMapping("/search")
    public Map<String,String> returnUniversityDetails(@RequestParam(value="university_name", required = true) String location,
                                                      @RequestParam(value="university_year", required = true) String establishedYear){
        return universityService.returnUniversityDetails(location,establishedYear);

    }
}
