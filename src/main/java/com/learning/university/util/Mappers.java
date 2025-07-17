package com.learning.university.util;

import com.learning.university.entity.Student;
import com.learning.university.entity.University;
import com.learning.university.model.StudentDTO;

public class Mappers {

    public static Student getStudentFromStudentDTO(StudentDTO studentdto, University university) {
        Student student = new Student();
        student.setName(studentdto.getName());
        student.setEmail(studentdto.getEmail());
        student.setEnrolledDate(studentdto.getEnrollementDate());
        student.setUniversity(university);
        return student;
    }

    public static StudentDTO getStudentDTOFromStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setEnrollementDate(student.getEnrolledDate());
        return studentDTO;
    }
}
