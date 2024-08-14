package com.ideas2it.dao;

import com.ideas2it.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Integer> {
    List<Department> findByIsDeletedFalse();
    Department findByIsDeletedFalseAndDepartmentId(int departmentId);

}
