package com.learning.university.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="University")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="location")
    private String location;

    @Column(name="establishedYear")
    private String establishedYear;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="uni_id")
    private Set<Student> students;

    public University(){
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
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

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", establishedYear='" + establishedYear + '\'' +
                ",students=" +students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof University that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(establishedYear, that.establishedYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, establishedYear);
    }

}
