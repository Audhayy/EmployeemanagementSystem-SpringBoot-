package com.ideas2it.controller;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.service.DepartmentService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;

    /**
     * <p>
     *This method is used to create a department into the repository
     * </p>
     */
    @PostMapping()
    public ResponseEntity<DepartmentDto> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>((savedDepartment), HttpStatus.CREATED);
    }
    /**
     * <p>
     *This method is used to show all the departments in the repository
     * </p>
     */
    @GetMapping()
    public ResponseEntity<List<DepartmentDto>>  DisplayAllDepartments() {
        List<DepartmentDto> departments  = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);

    }
    /**
     * <p>
     *This method is used to show a specific department in the repository
     * @param departmentId - unique identifier of the department
     * </p>
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> displayDepartment(@PathVariable("id") int departmentId) {
        DepartmentDto showdepartmentDto = departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>((showdepartmentDto),HttpStatus.OK);
    }
    /**
     * <p>
     *This method is used to create a department into the repository
     * @param departmentId -unique identifier of department
     * </p>
     */
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") int departmentId,@RequestBody DepartmentDto departmentDto) {
        DepartmentDto updateDepartmentDto =  departmentService.updateDepartment(departmentId,departmentDto);
        logger.info("Department details have been updated of id..{}",departmentId);
        return new ResponseEntity<>((updateDepartmentDto),HttpStatus.OK);
    }
    /**
     * <p>
     *This method is used to delete a department from the repository
     * @param departmentId
     * </p>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int departmentId) {
        departmentService.removeDepartment(departmentId);
        logger.info("Department has been deleted..{}",departmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/employees/{departmentId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartmentId(@PathVariable("departmentId") int departmentId) {
        List<EmployeeDto> employees = departmentService.getEmployeeByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}


