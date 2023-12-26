package com.samanvay.springboot.tutorial.repository;

import com.samanvay.springboot.tutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private String departmentNameToTest = "IT (Mechanical Engineering)";
    private Long departmentIdToTest = 1L;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName(departmentNameToTest)
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-ME-11")
                .build();

        entityManager.persist(department);
    }

    @Test
    @DisplayName ("When DB is called to find department by ID: should return the found department")
    public void whenFindByDepartmentId_ReturnTheFoundDepartment () {
        Department department = departmentRepository.findById(departmentIdToTest).get();

        assertEquals(department.getDepartmentName(), departmentNameToTest);
    }
}