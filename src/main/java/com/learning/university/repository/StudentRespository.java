package com.learning.university.repository;

import com.learning.university.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRespository extends JpaRepository<Student,Integer> {
}
