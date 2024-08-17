package com.ideas2it.dao;

import com.ideas2it.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {
    List<Employee> findByIsDeletedFalse();
    Employee findByIsDeletedFalseAndEmployeeId(int employeeId);
    public boolean existsByEmployeeName(String employeeName) ;

}
