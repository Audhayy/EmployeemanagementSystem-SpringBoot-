package com.ideas2it.department.service;

import java.util.List;


import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.model.Department;
import com.ideas2it.department.dao.DepartmentDao;
import com.ideas2it.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
    *<p>
    *This class acts as a Interface for the DepartmentServiceImpl.
    *the methods in the Service class is called for abstraction purpose
    *</p>
    */
    @Service
    public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }


    public Department addDepartment(Department department) {
        return departmentDao.save(department);

    }

    public List<Department> getAllDepartment() {
        return (List<Department>) departmentDao.findAll();

        
    }
    public void removeDepartment(int id) {
        departmentDao.deleteById(id);
    }

    public Department updateDepartment(int id, Department department)  {
        Department existingDepartment =  departmentDao.findById(id).orElse(null);
        existingDepartment.setDepartmentName(department.getDepartmentName());
        return departmentDao.save(existingDepartment);
    }

    public Department getDepartmentById(int id) {
        Department department = departmentDao.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(("Department not found with ID:" + id)));
        return department;
    }
    public List<Employee> getEmployeeByDepartmentId(int departmentId) {
        Department department = departmentDao.findById(departmentId).orElse(null);
        List<Employee> isEmployee = new ArrayList<>();
        for(Employee employee : department.getEmployees()) {
            if(!employee.isDeleted()) {
                isEmployee.add(employee);
            }
        }
        return isEmployee;
    }

}


  