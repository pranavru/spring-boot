package com.samanvay.springboot.tutorial.service;

import com.samanvay.springboot.tutorial.entity.Department;
import com.samanvay.springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("IT")
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .departmentId(1L)
                .build();
    }

    @Test
    @DisplayName ( "Given a valid department name: when department exists: should return the department" )
    public void whenValidDepartmentName_thenDepartmentIsFound () {
        String departmentName = "IT";
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase(departmentName)).thenReturn(department);

        Department response = departmentService.fetchDepartmentByName(departmentName);

        Assertions.assertEquals(response.getDepartmentName(), departmentName);
    }
}