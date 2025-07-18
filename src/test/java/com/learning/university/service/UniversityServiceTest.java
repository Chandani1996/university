package com.learning.university.service;

import com.learning.university.entity.Student;
import com.learning.university.entity.University;
import com.learning.university.model.StudentDTO;
import com.learning.university.model.UniversityDTO;
import com.learning.university.repository.UniversityRespository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversityServiceTest {

    @Test
    void testSaveUniversityDetails_withStudents() {
        // Mock repository
        UniversityRespository universityRespository = mock(UniversityRespository.class);

        // Inject into service manually
        UniversityService universityService = new UniversityService();
        universityService.universityRespository = universityRespository;

        // Prepare input DTO
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Alice");
        studentDTO.setEmail("alice@example.com");
        studentDTO.setEnrollementDate(String.valueOf(LocalDate.of(2023, 6, 1)));

        UniversityDTO request = new UniversityDTO();
        request.setName("Test University");
        request.setLocation("New York");
        request.setEstablishedYear(String.valueOf(1990));
        request.setStudentDTOList(List.of(studentDTO));

        // Prepare saved entity
        Student savedStudent = new Student();
        savedStudent.setName("Alice");
        savedStudent.setEmail("alice@example.com");
        savedStudent.setEnrolledDate(String.valueOf(LocalDate.of(2023, 6, 1)));

        University savedUniversity = new University();
        savedUniversity.setName("Test University");
        savedUniversity.setLocation("New York");
        savedUniversity.setEstablishedYear(String.valueOf(1990));
        savedUniversity.setStudents(Set.of(savedStudent));

        // Mock behavior
        when(universityRespository.save(any(University.class))).thenReturn(savedUniversity);

        // Call service
        UniversityDTO response = universityService.saveUniversityDetails(request);

        // Assertions
        assertNotNull(response);
        assertEquals("Test University", response.getName());
        assertEquals("New York", response.getLocation());
        assertEquals(1, response.getStudentDTOList().size());

        StudentDTO responseStudent = response.getStudentDTOList().getFirst();
        assertEquals("Alice", responseStudent.getName());
        assertEquals("alice@example.com", responseStudent.getEmail());

        // Verify save
        verify(universityRespository, times(1)).save(any(University.class));
    }

    @Test
    void testSaveUniversityDetails_withoutStudents() {
        // Mock repository
        UniversityRespository universityRespository = mock(UniversityRespository.class);

        // Inject into service manually
        UniversityService universityService = new UniversityService();
        universityService.universityRespository = universityRespository;

        // Prepare input DTO without students
        UniversityDTO request = new UniversityDTO();
        request.setName("Solo University");
        request.setLocation("Delhi");
        request.setEstablishedYear(String.valueOf(2000));
        request.setStudentDTOList(Collections.emptyList());

        University savedUniversity = new University();
        savedUniversity.setName("Solo University");
        savedUniversity.setLocation("Delhi");
        savedUniversity.setEstablishedYear(String.valueOf(2000));
        savedUniversity.setStudents(Collections.emptySet());

        // Mock save
        when(universityRespository.save(any(University.class))).thenReturn(savedUniversity);

        // Call service
        UniversityDTO response = universityService.saveUniversityDetails(request);

        // Assertions
        assertNotNull(response);
        assertEquals("Solo University", response.getName());
        assertEquals("Delhi", response.getLocation());
        assertTrue(response.getStudentDTOList() == null || response.getStudentDTOList().isEmpty());

        verify(universityRespository, times(1)).save(any(University.class));
    }

    @Test
    void testReturnUniversityDetails(){
        try {
            UniversityService universityService = new UniversityService();
            UniversityRespository universityRespository = mock(UniversityRespository.class);
            universityService.universityRespository = universityRespository;

            when(universityRespository.findAllByLocationAndEstablishedYear("Bengaluru", "18-07-2001"))
                    .thenReturn(null);

            universityService.returnUniversityDetails("Bengaluru", "18-07-2001");
        }
        catch (IllegalArgumentException e){
            assertEquals("No university found for the give location and year",e.getMessage());
        }
    }
}

