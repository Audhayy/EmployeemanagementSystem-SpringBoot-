package com.ideas2it.employee.service;

import java.util.List;

import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 *<p>
 *This class acts as a Interface for the EmployeeServiceImpl.
 *the methods in the Service class is called for abstraction purpose
 *</p>
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee createEmployee(Employee employee) {
        return employeeDao.save(employee);

    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeDao.findAll();


    }
    public void removeEmployee(int id) {
        Employee employee = employeeDao.findById(id).orElse(null);
        employee.setDeleted(true);
        employeeDao.save(employee);

    }

    public Employee updateEmployee(int id, Employee employee)  {
        Employee existingEmployee =  employeeDao.findById(id).orElse(null);
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        return employeeDao.save(existingEmployee);
    }

    public Employee getEmployee(int id) {
        Employee employee = employeeDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Employee not found with ID:" + id)));
        return employee;
    }

}


