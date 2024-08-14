package com.ideas2it.dto;


import com.ideas2it.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDto {

    private int employeeId;

    private String employeeName;

    private LocalDate employeeDOB;

    private int departmentId;

    private String departmentName;

    private int passportNumber;

    private String countryName;

    private int employeeSalary;

    private String employeeEmail;

    private String phoneNumber;

    private Set<Project> projectList;
    public EmployeeDto() {}
}
