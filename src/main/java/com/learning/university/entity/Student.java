package com.learning.university.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="enrolled_date")
    private String enrolledDate;



    @ManyToOne
    @JoinColumn(name="uni_id")
    private University university;

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(String enrolledDate) {
        this.enrolledDate = String.valueOf(enrolledDate);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", enrolledDate=" + enrolledDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(enrolledDate, student.enrolledDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, enrolledDate);
    }
}
