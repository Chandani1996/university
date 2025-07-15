package com.learning.university.service;

import com.learning.university.entity.Student;
import com.learning.university.entity.University;
import com.learning.university.model.AddStudentDetailsRequest;
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
    void testAddStudentDetails_shouldAddAndReturnUpdatedDetails() {
        // Mock repository
        UniversityRespository universityRespository = mock(UniversityRespository.class);

        // Inject into service manually
        UniversityService universityService = new UniversityService();
        universityService.universityRespository = universityRespository;

        // Arrange
        String universityName = "Oxford University";

        AddStudentDetailsRequest inputRequest = new AddStudentDetailsRequest();
        inputRequest.setName(universityName);

        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setName("John Doe");
        studentDTO1.setEmail("john@example.com");
        studentDTO1.setEnrollementDate(String.valueOf(LocalDate.of(2024, 1, 10)));

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setName("Jane Smith");
        studentDTO2.setEmail("jane@example.com");
        studentDTO2.setEnrollementDate(String.valueOf(LocalDate.of(2024, 2, 15)));

        inputRequest.setStudentDTOList(List.of(studentDTO1, studentDTO2));

        // Mock existing university with students
        University existingUniversity = new University();
        existingUniversity.setName(universityName);
        existingUniversity.setStudents(new HashSet<>()); // No students initially

        // Act: when findByName is called, return the mock university
        when(universityRespository.findByName(universityName)).thenReturn(existingUniversity);

        // Mock save to return university with students
        when(universityRespository.save(any(University.class))).thenAnswer(invocation -> {
            University uni = invocation.getArgument(0);

            // Mock bi-directional link
            for (Student s : uni.getStudents()) {
                s.setUniversity(uni);
            }
            return uni;
        });

        // Act
        AddStudentDetailsRequest result = universityService.addStudentDetails(inputRequest);

        // Assert
        assertEquals(universityName, result.getName());
        assertEquals(2, result.getStudentDTOList().size());

        List<String> names = result.getStudentDTOList().stream().map(StudentDTO::getName).toList();
        assertTrue(names.contains("John Doe"));
        assertTrue(names.contains("Jane Smith"));

        // Verify repository methods
        verify(universityRespository).findByName(universityName);
        verify(universityRespository).save(any(University.class));
    }
}

