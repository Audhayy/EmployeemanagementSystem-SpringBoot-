package com.ideas2it.controller;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Mock
    DepartmentService departmentService;

    @InjectMocks
    DepartmentController departmentController;

    private DepartmentDto departmentDto;
    private List<DepartmentDto> departmentDtos;
    private List<EmployeeDto> employeeDtos;

    @BeforeEach
    void setUp() {
        departmentDto = new DepartmentDto(1, "Admin");
    }

    @Test
    public void testAddDepartment() {
        when(departmentService.addDepartment(departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> createdDepartment = departmentController.addDepartment(departmentDto);
        assertEquals(HttpStatus.CREATED, createdDepartment.getStatusCode());
    }

    @Test
    public void testGetDepartments() {
        when(departmentService.getAllDepartment()).thenReturn(departmentDtos);
        ResponseEntity<List<DepartmentDto>>  retrieveDepartments = departmentController.DisplayAllDepartments();
        assertEquals(HttpStatus.OK, retrieveDepartments.getStatusCode());
    }

    @Test
    void testGetDepartmentById() {
        when(departmentService.getDepartmentById(1)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> retrieveDepartmentById = departmentController.displayDepartment(1);
        assertEquals(HttpStatus.OK, retrieveDepartmentById.getStatusCode());
    }

    @Test
    void testUpdateDepartment() {
        when(departmentService.updateDepartment(1,departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> updatedDepartment = departmentController.updateDepartment(1,departmentDto);
        assertEquals(HttpStatus.OK, updatedDepartment.getStatusCode());
    }

    @Test
    void getEmployeeByDepartment() {
        when(departmentService.getEmployeeByDepartmentId(1)).thenReturn(employeeDtos);
        ResponseEntity<List<EmployeeDto>> retrieveEmployeeByDepartment = departmentController.getEmployeesByDepartmentId(1);
        assertEquals(HttpStatus.OK, retrieveEmployeeByDepartment.getStatusCode());
    }
}