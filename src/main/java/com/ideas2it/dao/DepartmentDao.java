package com.ideas2it.dao;

import com.ideas2it.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *CRUD operations for department is performed in this class
 */
@Repository
public interface DepartmentDao extends CrudRepository<Department, Integer> {
    /**
     * <p>
     *This method is used to display departments that are found to be soft deleted
     * </p>
     */
    List<Department> findByIsDeletedFalse();
    /**
     * <p>
     *This method is returns a single department that is soft deleted
     * </p>
     */
    Department findByIsDeletedFalseAndDepartmentId(int departmentId);
    /**
     * <p>
     *This method is used to check the repository if the name already exist
     * </p>
     */
    public boolean existsByDepartmentName(String departmentName) ;

}
