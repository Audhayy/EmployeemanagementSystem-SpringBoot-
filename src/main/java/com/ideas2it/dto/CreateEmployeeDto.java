package com.ideas2it.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
/**
 *This class is used to be the dto when an employee is being created
 *It includes most of the members present in the model such as employeeId,employeeDOb
 * and many others
 * also is used for validation while creating details for employee
 */
public class CreateEmployeeDto {

    private int employeeId;

    @NotBlank(message = "name is a mandatory field")
    @Pattern(regexp = "(/^[A-Za-z]+$/) ",message = "employee name must only contain alphabets")
    private String employeeName;

    @Past(message = "Age cannot be set in the future")
    private LocalDate employeeDOB;

    private int departmentId;

    @NotBlank(message = "department name is a mandatory field and cannot be empty")
    @Pattern(regexp = "(/^[A-Za-z]+$/) ",message = "department name must only contain alphabets")
    private String departmentName;

    private int passportNumber;

    @Pattern(regexp = "(/^[A-Za-z]+$/) ",message = "country name must only contain alphabets")
    private String countryName;

    @PositiveOrZero(message = "salary cannot be negative")
    private int employeeSalary;

    @Email(message = "email is not in the appropriate format")
    private String employeeEmail;

    @Size(min = 10, max = 10,message = "Phone number must be of 10 digits")
    private String phoneNumber;

    @Size(max = 60,message = "age of employee cannot exceed 60")
    private String employeeAge;

    public CreateEmployeeDto() {

    }
}
