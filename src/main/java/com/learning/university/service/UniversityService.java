package com.learning.university.service;

import com.learning.university.entity.Student;
import com.learning.university.entity.University;
import com.learning.university.model.StudentDTO;
import com.learning.university.model.UniversityDTO;
import com.learning.university.repository.UniversityRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UniversityService {

    @Autowired
    UniversityRespository universityRespository;

    public UniversityDTO saveUniversityDetails(UniversityDTO universityRequest) {
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
        return universityResponse;

    }

    public List<String> displayUniversityStudentDetails(Integer id) {
        List<String> studentList = new ArrayList<>();
        University saveduniversity = universityRespository.findAllById(id);
        if (!Objects.nonNull(saveduniversity)) {
            throw new IllegalArgumentException(" No  value found for the given University");
        }

        studentList = saveduniversity.getStudents().stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        if (studentList.isEmpty()) {
            System.out.println("No students found for the given university");
        }
        return studentList;
    }
}
