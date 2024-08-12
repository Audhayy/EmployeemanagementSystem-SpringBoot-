package com.ideas2it.employee.dao;

import com.ideas2it.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

}
