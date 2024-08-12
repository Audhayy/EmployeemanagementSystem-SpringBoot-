package com.ideas2it.employee.dto;

import com.ideas2it.model.Department;
import com.ideas2it.model.Passport;
import com.ideas2it.model.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private int employeeId;

    private String employeeName;

    private LocalDate employeeDOB;

    private int departmentId;

    private String departmentName;

    private Passport passport;

    private int employeeSalary;

    private String employeeEmail;

    private String phoneNumber;

    private Set<Project> projectList;

}
