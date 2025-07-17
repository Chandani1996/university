package com.learning.university.service;

import com.learning.university.entity.Student;
import com.learning.university.entity.University;
import com.learning.university.model.StudentDTO;
import com.learning.university.model.UniversityDTO;
import com.learning.university.repository.UniversityRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    @Autowired
    UniversityRespository universityRespository;

    public UniversityDTO saveUniversityDetails(UniversityDTO universityRequest){
        University university = new University();
        university.setName(universityRequest.getName());
        university.setLocation(universityRequest.getLocation());
        university.setEstablishedYear(universityRequest.getEstablishedYear());
        if (universityRequest.getStudentDTOList() != null && !universityRequest.getStudentDTOList().isEmpty()) {
            Set<Student> students = universityRequest.getStudentDTOList().stream().map(s -> {
                Student student = new Student();
                student.setName(s.getName());
                student.setEmail(s.getEmail());
                student.setEnrolledDate(s.getEnrollementDate());
                student.setUniversity(university); // important for FK
                return student;
            }).collect(Collectors.toSet());

            university.setStudents(students);
        }
        University savedUniversity = universityRespository.save(university);

        UniversityDTO universityResponse = new UniversityDTO();
        universityResponse.setName(savedUniversity.getName());
        universityResponse.setLocation(savedUniversity.getLocation());
        universityResponse.setEstablishedYear(savedUniversity.getEstablishedYear());

        if (savedUniversity.getStudents() != null && !savedUniversity.getStudents().isEmpty()) {
            List<StudentDTO> studentDTOList = savedUniversity.getStudents().stream().map(s -> {
                StudentDTO dto = new StudentDTO();
                dto.setName(s.getName());
                dto.setEmail(s.getEmail());
                dto.setEnrollementDate(s.getEnrolledDate());
                return dto;
            }).collect(Collectors.toList());

            universityResponse.setStudentDTOList(studentDTOList);
        }
        return  universityResponse;

    }

    public Map<String,String> returnUniversityDetails(String location, String establishedYear) {

        Map<String,String> universityList = new HashMap<>();

        List<University> universities = universityRespository.findAllByLocationAndEstablishedYear(location,establishedYear);

        universityList= universities.stream()
                .collect(Collectors.toMap(University::getName,University::getEstablishedYear));
        return universityList;
    }
}
