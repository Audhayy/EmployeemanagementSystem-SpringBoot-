package com.ideas2it.mapper;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.model.Employee;

public class EmployeeMapper {
    public static EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setEmployeeName(employee.getEmployeeName());
        dto.setEmployeeDOB(employee.getEmployeeDOB());
        dto.setDepartmentId(employee.getDepartment().getDepartmentId());
        dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setEmployeeEmail(employee.getEmployeeEmail());
        dto.setEmployeeSalary(employee.getEmployeeSalary());
        dto.setPassportNumber(employee.getPassport().getPassportNumber());
        dto.setCountryName(employee.getPassport().getCountryName());
        dto.setProjectList(employee.getProjectList());
        return dto;
    }

    /**
     * <p>
     * This method Convert the Dto Object to Employee Object
     * @param employeeDto - Employee Dto
     * @return Employee - Employee as Object
     * </p>
     */
    public static Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeDOB(employeeDto.getEmployeeDOB());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employee.setEmployeeSalary(employeeDto.getEmployeeSalary());
        return employee;
    }
}
