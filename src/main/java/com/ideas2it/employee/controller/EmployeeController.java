package com.ideas2it.employee.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.department.service.DepartmentService;
import com.ideas2it.employee.dto.EmployeeDto;

import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *This class is the Controller for Employee get all the Information's
 * about the Employee and also various methods like
 * Create Employee Record
 * Display the Employee record(s)
 * Update the Employee Record
 * Delete the Employee Record in the Repository
 *</p>
 * @author Audhithiyah
 */
@RestController
@RequestMapping ("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * <p>
     * This method used to get the credentials
     * by the User to create EmployeeRecord.
     * </p>
     */
    @PostMapping ("/add")
    public ResponseEntity<EmployeeDto> createEmployeeRecords(@RequestBody EmployeeDto employeeDto) {
        Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
        Employee employee = convertToEntity(employeeDto);
        employee.setDepartment(department);
        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(convertToDto(savedEmployee), HttpStatus.CREATED);
    }

    /**
     * <p>
     * This method to Display
     * all the Records from the List
     * Retrieving from the Database
     * </p>
     */
    @GetMapping ("/get")
    public ResponseEntity<List<EmployeeDto>> displayRecords() {
        List<EmployeeDto> result = new ArrayList<>();
        List<Employee> Employees = employeeService.getAllEmployees();
        for (Employee employee : Employees) {
            if (!employee.isDeleted()){
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
    @GetMapping ("/get/{id}")
    public ResponseEntity<EmployeeDto> displayEmployeeRecord(@PathVariable ("id") int employeeId) {
        return new ResponseEntity<>(convertToDto(employeeService.getEmployee(employeeId)), HttpStatus.OK);
    }

    /**
     * <p>
     * This Method to Update an Employee Record
     * in the List with the Employee Id
     * </p>
     */
    @PutMapping ("update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeRecord(@PathVariable("id") int employeeId, @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(convertToDto(employeeService.updateEmployee(employeeId, convertToEntity(employeeDto))), HttpStatus.OK);
    }

    /**
     * <p>
     * This method to Delete an Employee Record in the Database
     * with the Employee Id
     * </p>
     */
    @DeleteMapping ("delete/{id}")
    public void deleteEmployeeRecord(@PathVariable("id") int employeeId) {
        employeeService.removeEmployee(employeeId);
    }

    /**
     * This method convert the Employee Object to Dto Object
     * @param employee - Employee as Object
     * @return - EmployeeDto as Dto Object
     */
    public static EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setEmployeeName(employee.getEmployeeName());
        dto.setEmployeeDOB(employee.getEmployeeDOB());
        dto.setDepartmentId(employee.getDepartment().getDepartmentId());
        dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setEmployeeEmail(employee.getEmployeeEmail());
        dto.setEmployeeSalary(employee.getEmployeeSalary());
        return dto;
    }

    /**
     * This method Convert the Dto Object to Employee Object
     * @param employeeDto - Employee Dto
     * @return Employee - Employee as Object
     */
    public static Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeDOB(employeeDto.getEmployeeDOB());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employee.setEmployeeSalary(employeeDto.getEmployeeSalary());
        return employee;
    }

    public static Department mapToDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartmentId(departmentDto.getId());
        department.setDepartmentName(departmentDto.getName());
        return department;
    }
}
