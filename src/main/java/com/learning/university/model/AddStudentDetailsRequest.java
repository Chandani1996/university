package com.learning.university.model;

import java.util.List;
import java.util.Objects;

public class AddStudentDetailsRequest {
    private String name;
    private List<StudentDTO> studentDTOList;

    public AddStudentDetailsRequest() {
    }

    public AddStudentDetailsRequest(String name, List<StudentDTO> studentDTOList) {
        this.name = name;
        this.studentDTOList = studentDTOList;
    }

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

    @Override
    public String toString() {
        return "AddStudentDetailsRequest{" +
                "name='" + name + '\'' +
                ", studentDTOList=" + studentDTOList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddStudentDetailsRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(studentDTOList, that.studentDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentDTOList);
    }
}

