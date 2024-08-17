package com.ideas2it.controller;

import com.ideas2it.dto.CreateEmployeeDto;
import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.mapper.DepartmentMapper;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.mapper.ProjectMapper;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Passport;
import com.ideas2it.model.Project;
import com.ideas2it.service.DepartmentService;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static com.ideas2it.mapper.EmployeeMapper.convertEntityToDto;
import static com.ideas2it.mapper.EmployeeMapper.convertToDto;

/**
 * <p>
 * This class is the Controller for Employee get all the Information's
 * about the Employee and also various methods like
 * Create Employee Record
 * Display the Employee record(s)
 * Update the Employee Record
 * Delete the Employee Record in the Repository
 * </p>
 *
 * @author Audhithiyah
 */
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
private static final Logger logger = LogManager.getLogger();
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ProjectService projectService;

    /**
     * <p>
     * This method used to get the credentials
     * by the User to create EmployeeRecord.
     * </p>
     */
    @PostMapping()
    public ResponseEntity<CreateEmployeeDto> createEmployeeRecords(@Valid @RequestBody EmployeeDto employeeDto) throws SQLIntegrityConstraintViolationException {
        DepartmentDto departmentDto = departmentService.getDepartmentById(employeeDto.getDepartmentId());
        Department department = DepartmentMapper.convertDtoToEntity(departmentDto);
        Passport passport = new Passport(employeeDto.getPassportNumber(), employeeDto.getCountryName());
        Employee employee = EmployeeMapper.convertToEntity(employeeDto);
        employee.setDepartment(department);
        employee.setPassport(passport);
        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(convertEntityToDto(savedEmployee), HttpStatus.CREATED);
    }

    /**
     * <p>
     * This method to Display
     * all the Records from the List
     * Retrieving from the Database
     * </p>
     */
    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> displayRecords() {
        List<EmployeeDto> result = new ArrayList<>();
        List<Employee> employees = employeeService.getAllEmployees();
        if(employees.isEmpty()) {
            logger.warn("Employee list is empty");
        } else {
            for (Employee employee : employees) {
                result.add(convertToDto(employee));
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * <p>
     * This Method is used to display the
     * Specific Employee Record in the list
     * retrieving from the Database
     * </p>
     */
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> displayEmployeeRecord(@PathVariable("id") int employeeId) {
        return new ResponseEntity<>(convertToDto(employeeService.getEmployee(employeeId)), HttpStatus.OK);
    }

    /**
     * <p>
     * This Method to Update an Employee Record
     * in the List with the Employee Id
     * </p>
     */
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeRecord(@PathVariable("id") int employeeId, @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(convertToDto(employeeService.updateEmployee(employeeId, EmployeeMapper.convertToEntity(employeeDto))), HttpStatus.OK);
    }

    /**
     * <p>
     * This method to Delete an Employee Record in the Database
     * with the Employee Id
     * </p>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployeeRecord(@PathVariable("id") int employeeId) {
        employeeService.removeEmployee(employeeId);
        logger.info("Employee has been deleted successfully..{}",employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{employeeId}/assign/{projectId}")
    public ResponseEntity<EmployeeDto> assignEmployeeToProject(@PathVariable int employeeId, @PathVariable int projectId) {
        ProjectDto projectDto = projectService.getProjectById(projectId);
        Project project = ProjectMapper.convertDtoToEntity(projectDto);
        Employee employee = employeeService.assignEmployee(employeeId, project);
        return new ResponseEntity<>(EmployeeMapper.convertToDto(employee), HttpStatus.OK);


    }
}
