package com.samanvay.springboot.tutorial.controller;

import com.samanvay.springboot.tutorial.entity.Department;
import com.samanvay.springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private DepartmentService departmentService;

    private Department department;
    private String departmentNameToTest = "IT";
    private Long departmentIdToTest = 1L;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName(departmentNameToTest)
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .departmentId(departmentIdToTest)
                .build();
    }

    @Test
    @DisplayName (value = "Given Department POST call: when department details are passed: should save the department")
     void saveDepartment() throws Exception {
        Department departmentToUpdate = Department.builder()
                .departmentName(departmentNameToTest)
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .build();

        Mockito.when(departmentService.saveDepartment(departmentToUpdate)).thenReturn(department);

        String contentToTest = "{\n" +
                "    \"departmentName\": \"IT\",\n" +
                "    \"departmentCode\": \"IT-06\", \n" +
                "    \"departmentAddress\": \"Ahmedabad\"\n" +
                "}";

        MockHttpServletRequestBuilder actionToPerform =
                post("/departments").contentType(MediaType.APPLICATION_JSON).content(contentToTest);

        mockMVC.perform(actionToPerform).andExpect(status().isOk());
    }

    @Test
    @DisplayName (value = "Given Department GET call: when department id is passed: should return the department")
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(departmentIdToTest)).thenReturn(department);

        MockHttpServletRequestBuilder actionToPerform =
                get("/departments/1").contentType(MediaType.APPLICATION_JSON);

        mockMVC.perform(actionToPerform)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }

    @Test
    @DisplayName (value = "Given Department GET call: when nothing is passed: should return a list of the departments")
    void fetchDepartment() throws Exception {
        List<Department> departments = new ArrayList<Department>();
        departments.add(department);

        Mockito.when(departmentService.fetchDepartmentList()).thenReturn(departments);

        MockHttpServletRequestBuilder actionToPerform =
                get("/departments").contentType(MediaType.APPLICATION_JSON);

        mockMVC.perform(actionToPerform)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName (value = "Given Department DELETE call: when department id is passed: should delete the department")
    void deleteDepartmentById() throws Exception {
        String expectedResult = "Department deleted";

        MockHttpServletRequestBuilder actionToPerform =
                delete("/departments/1").contentType(MediaType.APPLICATION_JSON);

        mockMVC.perform(actionToPerform)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName (value = "Given Department GET call: when department name is passed: should return the department")
    void fetchDepartmentByName() throws Exception {
        Mockito.when(departmentService.fetchDepartmentByName("Ahmedabad")).thenReturn(department);

        MockHttpServletRequestBuilder actionToPerform =
                get("/departments/name/Ahmedabad").contentType(MediaType.APPLICATION_JSON);

        mockMVC.perform(actionToPerform)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }

    @Test
    @DisplayName (value = "Given Department PUT call: when department details are passed: should update the department")
    void updateDepartment() throws Exception {
        Department departmentToUpdate = Department.builder()
                .departmentName(departmentNameToTest)
                .departmentAddress("Mumbai")
                .departmentCode("IT-06")
                .departmentId(departmentIdToTest)
                .build();

        Mockito.when(departmentService.updateDepartment(departmentIdToTest, departmentToUpdate)).thenReturn(department);

        String contentToTest = "{\n" +
                "    \"departmentName\": \"IT\",\n" +
                "    \"departmentCode\": \"IT-06\", \n" +
                "    \"departmentAddress\": \"Mumbai\"\n" +
                "}";

        MockHttpServletRequestBuilder actionToPerform =
                put("/departments/1").contentType(MediaType.APPLICATION_JSON).content(contentToTest);

        mockMVC.perform(actionToPerform).andExpect(status().isOk());
    }
}