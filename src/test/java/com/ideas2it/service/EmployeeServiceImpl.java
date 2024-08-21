package com.ideas2it.service;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 *<p>
 *This class acts as a Interface for the EmployeeServiceImpl.
 *the methods in the Service class is called for abstraction purpose
 *</p>
 */
@Service
public class EmployeeServiceImpl implements EmployeeServiceTest {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {

        this.employeeDao = employeeDao;
    }

    public Employee createEmployee(Employee employee) {
        if(employeeDao.existsByEmployeeName(employee.getEmployeeName())) {
            logger.warn("trying to add a duplicate entry of the employee");

        }
        logger.info("Employee created successfully with name:{}",employee.getEmployeeName());
        return employeeDao.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeDao.findAll();


    }
    public void removeEmployee(int id) {
        Employee employee = employeeDao.findByIsDeletedFalseAndEmployeeId(id);
        if(null == employee) {
            logger.warn("Employee does not exist to be deleted");
            throw new NoSuchElementException("Employee not found with id:" + id);
        }
        employee.setDeleted(true);
        employeeDao.save(employee);

    }

    public Employee updateEmployee(int id, Employee employee)  {
        Employee existingEmployee =  employeeDao.findByIsDeletedFalseAndEmployeeId(id);
        if(null==existingEmployee) {
            logger.warn("Employee is not present to be updated");
            throw new NoSuchElementException("there is no such element to be updated with id"+id);
        }
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        logger.info("Employee has been updated successfully");
        return employeeDao.save(existingEmployee);
    }

    public Employee getEmployee(int id) {
        Employee employee = employeeDao.findById(id).orElse(null);
        return employee;
    }
    public Employee assignEmployee(int employeeId, Project project) {
        Employee employee = employeeDao.findByIsDeletedFalseAndEmployeeId(employeeId);
        if(null == project) {
            if(null == employee) {
                logger.warn("Employee not found with id{}", employeeId);
                throw new NoSuchElementException("Employee not found with id:" + employeeId);
            }
            logger.warn("Project not found");
        }
        for(Project currentProject : employee.getProjectList()) {
            if (currentProject.getProjectId()== project.getProjectId()) {
                throw new EmployeeException("Employee has been already assigned");
            }
        }
        employee.getProjectList().add(project);
        logger.info("Employee has been assigned to the specified project");
        return employeeDao.save(employee);

    }


}


