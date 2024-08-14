package com.ideas2it.controller;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * This class is the Controller for department to get all the Information's
 * about the department and also various methods like
 * Create department Record
 * Display the department record(s)
 * Update the department Record
 * Delete the department Record in the Repository
 * </p>
 *
 * @author Audhithiyah
 */
@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;



    @PostMapping()
    private DepartmentDto addDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.addDepartment(departmentDto);
    }

    @GetMapping()
    public List<DepartmentDto> DisplayAllDepartments() {
        return departmentService.getAllDepartment();

    }
    @GetMapping("/{id}")
    public DepartmentDto displayDepartment(@PathVariable("id") int departmentId) {
       return departmentService.getDepartmentById(departmentId);
    }
    @PutMapping("{id}")
    public DepartmentDto updateDepartment(@PathVariable("id") int departmentId,@RequestBody DepartmentDto departmentDto) {
        return departmentService.updateDepartment(departmentId,departmentDto);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") int departmentId) {
        departmentService.removeDepartment(departmentId);
    }
    @GetMapping("/employees/{departmentId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartmentId(@PathVariable("departmentId") int departmentId) {
        List<EmployeeDto> employees = departmentService.getEmployeeByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}


