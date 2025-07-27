package com.learning.university.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class AddStudentDetailsRequest {
    private String name;
    private List<StudentDTO> studentDTOList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentDTO> getStudentDTOList() {
        return studentDTOList;
    }

    public void setStudentDTOList(List<StudentDTO> studentDTOList) {
        this.studentDTOList = studentDTOList;
    }
}

