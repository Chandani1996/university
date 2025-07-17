package com.learning.university.repository;

import com.learning.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRespository extends JpaRepository<University,Integer> {
    List<University> findAllByLocationAndEstablishedYear(String location, String establishedYear);
}
