package com.ideas2it.model;


import jakarta.persistence.*;

import java.util.Set;

/**
  *<p>
  *This is a class created to associate with employee and 
  *and has data members like department name,department id.
  *
  *@author Audhithiyah
  *</p>
  */

@Entity
@Table(name = "department")
public class Department {  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int departmentId;

    @Column(name ="department_name")
    private String departmentName;
  
    @OneToMany(mappedBy ="department", fetch = FetchType.EAGER)
    
    private Set<Employee> employees;
    private boolean isDeleted = false;

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Department() {}

    public Department(String departmentName,int departmentId) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
	
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
       this.departmentId = departmentId;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}


