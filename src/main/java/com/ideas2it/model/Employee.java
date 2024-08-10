package com.ideas2it.model;


import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import com.ideas2it.model.Passport;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


/**
 *This is a class created to associate with Department and 
 *projects and has data members like employee name,employee id, and employee email.
 *
 *@author Audhithiyah
 */
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;
  
    @Column(name ="name")
    private String employeeName;

    @Column(name = "date_of_birth")
    private LocalDate employeeDOB;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id")
    private Passport passport;
  
    @Column(name = "salary")
    private int employeeSalary;
  
    @Column(name = "email")
    private String employeeEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name ="is_deleted")
    private boolean softDelete;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "association_table",
        joinColumns = @JoinColumn(name = "employee_id"),           
        inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Project> projectList;
    
    public Employee() {}

    public Employee(String employeeName,LocalDate employeeDOB,
                    int employeeSalary, String employeeEmail,String PhoneNumber,
                    Department department, Passport passport) {
        this.employeeName = employeeName;
        this.employeeDOB = employeeDOB;
        this.employeeSalary = employeeSalary;
        this.employeeEmail = employeeEmail;
        this.phoneNumber= PhoneNumber;
        this.department = department; 
        this.passport = passport;
        this.softDelete = false;  
    }  

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getEmployeeDOB(){
        return employeeDOB;
    }

    public void setEmployeeDOB(LocalDate employeeDOB){
        this.employeeDOB = employeeDOB;
    }

    /**
    * getAge()calculates the number of full years between 
    * the employeeDOB and the current date
    */
    public int getAge(){
        return Period.between(employeeDOB,LocalDate.now()).getYears();
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeEmail() {  
        return employeeEmail;  
    } 
 
    public void setEmployeeEmail(String EmployeeEmail) {  
        this.employeeEmail = EmployeeEmail;  
    } 
 
    public String getPhoneNumber() {  
        return phoneNumber;  
    }  

    public void setPhoneNumber(String phoneNumber) {  
        this.phoneNumber = phoneNumber;
    }

    public Department getDepartment() {
      return department;  
    } 
 
    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Passport getPassport() {
        return passport;
    }

    public Set<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(Set<Project> projectList) {
        this.projectList = projectList;
    }	
    
    public boolean getsoftDelete() {
        return softDelete;
    }

    public void setsoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    } 
 
    public String getProjectNames() {
        StringBuilder projects = new StringBuilder();
        for (Project project : projectList) {
            projects.append(project.getProjectName()).append(",");
        }
        return projects.toString(); 
    }
}



