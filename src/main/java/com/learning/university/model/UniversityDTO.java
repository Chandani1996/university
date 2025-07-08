package com.learning.university.model;

import java.util.List;

public class UniversityDTO {

    private String name;
    private String location;
    private String establishedYear;

    private List<StudentDTO> studentDTOList;

    public UniversityDTO(String name, String location, String establishedYear, List<StudentDTO> studentDTOList) {
        this.name = name;
        this.location = location;
        this.establishedYear = establishedYear;
        this.studentDTOList = studentDTOList;
    }

    public UniversityDTO(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(String establishedYear) {
        this.establishedYear = establishedYear;
    }

    public List<StudentDTO> getStudentDTOList() {
        return studentDTOList;
    }

    public void setStudentDTOList(List<StudentDTO> studentDTOList) {
        this.studentDTOList = studentDTOList;
    }
}
