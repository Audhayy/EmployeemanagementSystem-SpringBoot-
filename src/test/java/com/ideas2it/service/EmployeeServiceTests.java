package com.ideas2it.service;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Passport;
import com.ideas2it.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private DepartmentService departmentService;

    private Employee employee;

    private Department department;

    @BeforeEach
    void setUp() {
        String dateOfBirth = "2002-04-12";
        LocalDate DOB = LocalDate.parse(dateOfBirth);
        Set<Project> Projects= new HashSet<>();
        Project project = new Project("teams");
        Projects.add(project);
        department = new Department(1,"Admin");
        Passport passport = new Passport(1,"India");


        employee = new Employee(1,"Audhi", DOB,
                department,passport,1234,"check@gmail.com",
                "12345678",false,Projects);
    }

    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList(){
        when(employeeDao.findAll()).thenReturn(List.of(employee));
        List<Employee> employees = employeeService.getAllEmployees();

        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(1);
    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        when(employeeDao.findById(1)).thenReturn(Optional.of(employee));
        Employee savedEmployee = employeeService.getEmployee(employee.getEmployeeId());
        assertThat(savedEmployee).isNotNull();
    }
    @Test
    public void testAddEmployee() {
        EmployeeDto requestDto = new EmployeeDto();
        requestDto.setEmployeeName("Admin");
        when(employeeDao.existsByEmployeeName("Audhi")).thenReturn(false);
        when(employeeDao.save(any(Employee.class))).thenReturn(employee);
        Employee savedEmployeeDto = employeeService.createEmployee(employee);
        assertThat(savedEmployeeDto.getEmployeeId()).isNotNull();
    }

}
