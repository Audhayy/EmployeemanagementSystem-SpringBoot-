package com.ideas2it.dao;

import com.ideas2it.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 *CRUD operations for employee is performed in this class
 */
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
    /**
     * <p>
     *This method is used to display employees that are found to be soft deleted
     * </p>
     */
    List<Employee> findByIsDeletedFalse();
    /**
     * <p>
     *This method is returns a single employee that is softdeleted
     * </p>
     */
    Employee findByIsDeletedFalseAndEmployeeId(int employeeId);
    /**
     * <p>
     *This method is used to check the repository if the name already exist
     * </p>
     */
    public boolean existsByEmployeeName(String employeeName) ;

}
