package com.samanvay.springboot.tutorial.service;

import com.samanvay.springboot.tutorial.entity.Department;
import com.samanvay.springboot.tutorial.errorHandling.DepartmentNotFoundException;
import com.samanvay.springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImplementation implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department =  departmentRepository.findById(departmentId);

        if(!department.isPresent()) {
            throw new DepartmentNotFoundException("Department not found!");
        }

        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    private Boolean checkIfValueExists (String value) {
        return Objects.nonNull(value) && !"".equalsIgnoreCase(value);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException {
        Optional<Department> departmentToUpdate =  departmentRepository.findById(departmentId);

        if(!departmentToUpdate.isPresent()) throw new DepartmentNotFoundException("Department not found!");

        String departmentName = department.getDepartmentName();
        String departmentAddress = department.getDepartmentCode();
        String departmentCode = department.getDepartmentAddress();

        Department temporaryDepartment = departmentToUpdate.get();

        if(checkIfValueExists(departmentName)) {
            temporaryDepartment.setDepartmentName(departmentName);
        }

        if(checkIfValueExists(departmentCode)) {
            temporaryDepartment.setDepartmentCode(departmentCode);
        }

        if(checkIfValueExists(departmentAddress)) {
            temporaryDepartment.setDepartmentAddress(departmentAddress);
        }

        return departmentRepository.save(temporaryDepartment);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
