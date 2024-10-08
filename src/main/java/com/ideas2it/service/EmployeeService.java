package com.ideas2it.service;


import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 *Class to implement interface for the employee Service
 *this interface provides methods for managing employee records.
 */
@Service
public interface EmployeeService {

    /**
     *Method implemented in serviceImpl for creating employee records.
     *@param employee - object of the employee to be added
     */
    public Employee createEmployee(Employee employee)throws SQLIntegrityConstraintViolationException;

    /**
     *Method implemented in ServiceImpl for showing the employee corresponding to the id.
     *@param id - id of the employee the employee is in
     */
    public void removeEmployee(int id);

    /**
     *Method implemented in ServiceImpl for showing all the employees available.
     */
    public List<Employee> getAllEmployees();

    /**
     * Method implemented in ServiceImpl to get update the employee details
     * that has been previously given.
     *
     * @param employee - object of the class employee
     * @param id - unique identifier of project
     */
    public Employee updateEmployee(int id, Employee employee);

    public Employee assignEmployee(int employeeId, Project project);
    /**
     *Method implemented in ServiceImpl to check if the employee id given by the employee
     *matches with the employee id in the employee.
     *@param id - unique identifier of the employee
     */
    public Employee getEmployee(int id);

}