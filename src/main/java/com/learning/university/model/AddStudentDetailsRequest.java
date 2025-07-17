package com.learning.university.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class AddStudentDetailsRequest {
    private String name;
    private List<StudentDTO> studentDTOList;
}

