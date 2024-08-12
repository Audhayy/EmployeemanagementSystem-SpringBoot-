package com.ideas2it.department.controller;


import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.department.service.DepartmentService;
import com.ideas2it.model.Employee;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    public static DepartmentDto convertToDto(Department department) {
        return new DepartmentDto(department.getDepartmentId(), department.getDepartmentName() );
    }

    public static Department convertToEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartmentId(departmentDto.getId());
        department.setDepartmentName(departmentDto.getName());
        return department;
    }

    @PostMapping("/add")
    private DepartmentDto addDepartment(@RequestBody DepartmentDto departmentDto) {
        return convertToDto(departmentService.addDepartment(convertToEntity(departmentDto)));
    }

    @GetMapping("/get")
    public List<DepartmentDto> DisplayAllDepartments() {
        List<DepartmentDto> result = new ArrayList<>();
        List<Department> Departments = departmentService.getAllDepartment();
        for (Department department : Departments) {
            if (!department.isDeleted()){
                result.add(convertToDto(department));
            }
        }
        return result;
    }
    @GetMapping("/{id}")
    public DepartmentDto displayDepartment(@PathVariable("id") int departmentId) {
       return convertToDto(departmentService.getDepartmentById(departmentId));
    }
    @PutMapping("/update/{id}")
    public DepartmentDto updateDepartment(@PathVariable("id") int departmentId,@RequestBody DepartmentDto departmentDto) {
        return convertToDto(departmentService.updateDepartment(departmentId, convertToEntity(departmentDto)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") int departmentId) {
        departmentService.removeDepartment(departmentId);
    }
    @GetMapping("/employees/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable("departmentId") int departmentId) {
        List<Employee> employees = departmentService.getEmployeeByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}


