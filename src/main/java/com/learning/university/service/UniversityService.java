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
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learning.university.util.Mappers.getStudentFromStudentDTO;
import static com.learning.university.util.Mappers.getStudentDTOFromStudent;

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
                Student student = getStudentFromStudentDTO(s, university);
                return student;
            }).collect(Collectors.toSet());

            university.setStudents(students);
        }
        University savedUniversity = universityRespository.save(university);
        List<StudentDTO> studentDTOList = new ArrayList<>();

        if (savedUniversity.getStudents() != null && !savedUniversity.getStudents().isEmpty()) {
            studentDTOList = savedUniversity.getStudents().stream().map(s -> {
                StudentDTO dto = getStudentDTOFromStudent(s);
                return dto;
            }).collect(Collectors.toList());
        }

        UniversityDTO universityResponse = new UniversityDTO();
        universityResponse.setName(savedUniversity.getName());
        universityResponse.setLocation(savedUniversity.getLocation());
        universityResponse.setEstablishedYear(savedUniversity.getEstablishedYear());
        universityResponse.setStudentDTOList(studentDTOList);

        return universityResponse;

    }

    public AddStudentDetailsRequest addStudentDetails(AddStudentDetailsRequest addStudentDetails) {

        University university = universityRespository.findByName(addStudentDetails.getName());

        Set<Student> exsistingStudents = university.getStudents();
        if(exsistingStudents == null){
            exsistingStudents = new HashSet<>();
        }

        List<StudentDTO> studentDtoList = addStudentDetails.getStudentDTOList();
        for (StudentDTO studentdto : studentDtoList) {
            Student student = getStudentFromStudentDTO(studentdto, university);
            exsistingStudents.add(student);
        }
        university.setStudents(exsistingStudents);

        University savedUniversity =universityRespository.save(university);

        AddStudentDetailsRequest addStudentDetailsRequest = new AddStudentDetailsRequest();

        Set<Student> students = savedUniversity.getStudents();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for(Student student: students){
            StudentDTO studentDTO = getStudentDTOFromStudent(student);
            studentDTOList.add(studentDTO);
        }
        addStudentDetailsRequest.setName(savedUniversity.getName());
        addStudentDetailsRequest.setStudentDTOList(studentDTOList);
        return addStudentDetailsRequest;
    }

    public List<String> fetchUniversityStudentnames(Integer id) {
        List<String> studentList = new ArrayList<>();
        University university = universityRespository.findAllById(id);
        if (!Objects.nonNull(university)) {
            throw new IllegalArgumentException("No value found for the given university");
        }

        studentList = university.getStudents().stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        if (studentList.isEmpty()) {
            System.out.println("No students found for the given university");
        }
        return studentList;
    }
}
