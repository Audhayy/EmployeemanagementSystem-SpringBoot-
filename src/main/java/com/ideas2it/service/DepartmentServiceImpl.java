package com.ideas2it.service;

import com.ideas2it.dao.DepartmentDao;
import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.mapper.DepartmentMapper;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
    *<p>
    *This class acts as a Interface for the DepartmentServiceImpl.
    *the methods in the Service class is called for abstraction purpose
    *</p>
    */
    @Service
    public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {

        this.departmentDao = departmentDao;
    }

    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.convertDtoToEntity(departmentDto);
        if(departmentDao.existsByDepartmentName(department.getDepartmentName())) {
            logger.warn("trying to add a duplicate entry of the department");
        }
        logger.info("Department has been successfully created");
        return DepartmentMapper.convertEntityToDto(departmentDao.save(department));

    }

    public List<DepartmentDto> getAllDepartment() {
        List<DepartmentDto> result = new ArrayList<>();
        List<Department> departments = departmentDao.findByIsDeletedFalse();
        if(departments.isEmpty()) {
            logger.warn("Department List is empty");
        } else {
            for (Department department : departments) {
                result.add(DepartmentMapper.convertEntityToDto(department));
            }
        }
        return result;
    }

    public void removeDepartment(int id) {
        Department department = departmentDao.findByIsDeletedFalseAndDepartmentId(id);
        department.setDeleted(true);
        departmentDao.save(department);
        logger.info("employee has been deleted of id ..{}",id);

    }

    public DepartmentDto updateDepartment(int id, DepartmentDto departmentDto)  {
        Department convertDepartment = DepartmentMapper.convertDtoToEntity(departmentDto);
        Department existingDepartment =  departmentDao.findByIsDeletedFalseAndDepartmentId(id);
        existingDepartment.setDepartmentName(convertDepartment.getDepartmentName());
        logger.info("employee has been updated of id..{}",id);
        return DepartmentMapper.convertEntityToDto(departmentDao.save(existingDepartment));
    }

    public DepartmentDto getDepartmentById(int id) {
        Department department = departmentDao.findByIsDeletedFalseAndDepartmentId(id);
        if(null == department) {
            throw new EmployeeException("Department Not found with Id : " + 1);
        }
        return DepartmentMapper.convertEntityToDto(department);
    }
    public List<EmployeeDto> getEmployeeByDepartmentId(int departmentId) {
        Department department = departmentDao.findByIsDeletedFalseAndDepartmentId(departmentId);
        List<EmployeeDto> employeesByDepartment = new ArrayList<>();
        List<Employee> employees = new ArrayList<>(department.getEmployees());
        for(Employee employee : employees) {
            if(!employee.isDeleted()) {
                employeesByDepartment.add(EmployeeMapper.convertToDto(employee));
            }
        }
        return employeesByDepartment;
    }

}


  