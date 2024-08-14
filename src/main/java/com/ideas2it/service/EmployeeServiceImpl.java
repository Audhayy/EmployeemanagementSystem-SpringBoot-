package com.ideas2it.service;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *This class acts as a Interface for the EmployeeServiceImpl.
 *the methods in the Service class is called for abstraction purpose
 *</p>
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {

        this.employeeDao = employeeDao;
    }

    public Employee createEmployee(Employee employee) {
        logger.info("Employee created successfully with name:{}",employee.getEmployeeName());
        return employeeDao.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeDao.findByIsDeletedFalse();


    }
    public void removeEmployee(int id) {
        Employee employee = employeeDao.findByIsDeletedFalseAndEmployeeId(id);
        employee.setDeleted(true);
        employeeDao.save(employee);

    }

    public Employee updateEmployee(int id, Employee employee)  {
        Employee existingEmployee =  employeeDao.findByIsDeletedFalseAndEmployeeId(id);
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        logger.info("Employee has been updated successfully");
        return employeeDao.save(existingEmployee);
    }

    public Employee getEmployee(int id) {
        Employee employee = employeeDao.findByIsDeletedFalseAndEmployeeId(id);
        return employee;
    }
    public Employee assignEmployee(int employeeId, Project project) {
        Employee employee = employeeDao.findByIsDeletedFalseAndEmployeeId(employeeId);
        employee.getProjectList().add(project);
        logger.info("Employee has been assigned to the specified project");
        return employeeDao.save(employee);

    }


}


