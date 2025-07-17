package com.learning.university.repository;

import com.learning.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRespository extends JpaRepository<University,Integer> {
    University findAllById(Integer id);

    University findByName(String name);

}
