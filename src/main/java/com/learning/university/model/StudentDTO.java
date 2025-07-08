package com.learning.university.model;

import java.util.Date;
import java.util.Objects;

public class StudentDTO {

    private String name;
    private String email;
    private String enrollementDate;

    public StudentDTO(String name, String email, String enrollementDate) {
        this.name = name;
        this.email = email;
        this.enrollementDate = enrollementDate;
    }

    public StudentDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnrollementDate() {
        return enrollementDate;
    }

    public void setEnrollementDate(String enrollementDate) {
        this.enrollementDate = enrollementDate;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", enrollementDate='" + enrollementDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDTO that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(enrollementDate, that.enrollementDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, enrollementDate);
    }

}
