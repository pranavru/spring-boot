package com.samanvay.springboot.tutorial.controller;

import com.samanvay.springboot.tutorial.entity.Department;
import com.samanvay.springboot.tutorial.errorHandling.DepartmentNotFoundException;
import com.samanvay.springboot.tutorial.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    /*
    * Department Service should give it to me instead of me manually adding the object using new keyword
    * Instead of this, "DepartmentService service = new DepartmentServiceImplementation();"
    * using Dependency Injection.
    */
    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping (value = "/departments")
    public Department saveDepartment (@Valid @RequestBody Department department) {
        LOGGER.info("Inside save department of DepartmentController");

        return departmentService.saveDepartment(department);
    }

    @GetMapping (value = "/departments")
    public List<Department> fetchDepartments () {
        LOGGER.info("Inside fetch department list of DepartmentController");

        return departmentService.fetchDepartmentList();
    }

    @GetMapping (value = "/departments/{id}")
    public Department fetchDepartmentById (@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping (value = "/departments/{id}")
    public String deleteDepartmentById (@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);

        return "Department successfully deleted";
    }

    @PutMapping (value = "/departments/{id}")
    public Department updateDepartment (@PathVariable("id") Long departmentId, @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department);
    }

    @GetMapping (value = "/departments/name/{name}")
    public Department fetchDepartmentByName (@PathVariable("name") String departmentName) {
        return departmentService.fetchDepartmentByName(departmentName);
    }
}
