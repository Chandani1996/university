package com.learning.university.service;

import com.learning.university.entity.Student;
import com.learning.university.entity.University;
import com.learning.university.model.AddStudentDetailsRequest;
import com.learning.university.model.StudentDTO;
import com.learning.university.model.UniversityDTO;
import com.learning.university.repository.UniversityRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public AddStudentDetailsRequest addStudentDetails(AddStudentDetailsRequest addStudentDetails) {

        University university = universityRespository.findByName(addStudentDetails.getName());

        Set<Student> exsistingStudents = university.getStudents();
        if(exsistingStudents == null){
            exsistingStudents = new HashSet<>();
        }

        List<StudentDTO> studentDtoList = addStudentDetails.getStudentDTOList();
        //Set<Student> studentList = new HashSet<>();
        for (StudentDTO studentdto : studentDtoList) {
            Student student = new Student();
            student.setName(studentdto.getName());
            student.setEmail(studentdto.getEmail());
            student.setEnrolledDate(studentdto.getEnrollementDate());
            student.setUniversity(university);
            exsistingStudents.add(student);
        }
        university.setStudents(exsistingStudents);

        University savedUniversity =universityRespository.save(university);

        AddStudentDetailsRequest addStudentDetailsRequest = new AddStudentDetailsRequest();

        Set<Student> students = savedUniversity.getStudents();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for(Student student: students){
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(student.getName());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setEnrollementDate(student.getEnrolledDate());
            studentDTOList.add(studentDTO);
        }
        addStudentDetailsRequest.setName(savedUniversity.getName());
        addStudentDetailsRequest.setStudentDTOList(studentDTOList);
        return addStudentDetailsRequest;
    }
}
