package com.ideas2it.department.service;

import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *Class to implement interface for the employee Service
 *this interface provides methods for managing employee records.
 */
@Service
public interface DepartmentService {
   /**
    *Method implemented in serviceImpl for creating department records.
    *@param departmentDto - name of the department to be added
    */
   public Department addDepartment(Department department);

   /**
    *Method implemented in ServiceImpl for showing the department corresponding to the id.
    *@param id - id of the department the employee is in
    */
   public void removeDepartment(int id);

   /**
    *Method implemented in ServiceImpl for showing all the departments available.
    */
   public List<Department> displayAllDepartment();

   /**
    *Method implemented in ServiceImpl for removing a department from the records.
    *@param id - unique identifier of the department
    *@throws EmployeeException when cannot be deleted
    */

   /**
    *Method implemented in ServiceImpl to check if the department id given by the employee
    *matches with the department id in the department.
    *@param id - unique identifier of the department
    *@throws EmployeeException while the id does cannot be fetched
    */

   /**
    * Method implemented in ServiceImpl to get update the department details
    * that has been previously given.
    *
    * @param department - object of the class department
    */
   public Department updateDepartment(int id, Department department);



    public Department displayDepartment(int id);
}   