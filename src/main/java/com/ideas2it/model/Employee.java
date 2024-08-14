package com.ideas2it.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


/**
 *This is a class created to associate with Department and 
 *projects and has data members like employee name,employee id, and employee email.
 *
 *@author Audhithiyah
 */
@Getter
@Setter
@AllArgsConstructor
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
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;
  
    @Column(name = "salary")
    private int employeeSalary;
  
    @Column(name = "email")
    private String employeeEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name ="is_deleted")
    private boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "association_table",
        joinColumns = @JoinColumn(name = "employee_id"),           
        inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Project> projectList;

    public String getProjectNames() {
        StringBuilder projects = new StringBuilder();
        for (Project project : getProjectList()) {
            projects.append(project.getProjectName()).append(",");
        }
        return projects.toString(); 
    }

    public Employee() {}
}



