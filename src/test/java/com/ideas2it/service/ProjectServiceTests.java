package com.ideas2it.service;

import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;
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
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTests {
    @Mock
    private ProjectDao projectDao;
    private ProjectDto projectDto;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private Employee employee;
    private Department department;


    @BeforeEach
    void setUp() {
        String dateOfBirth = "2002-04-12";
        LocalDate DOB = LocalDate.parse(dateOfBirth);
        Set<Project> Projects= new HashSet<>();
        project = new Project(1,"teams");
        Projects.add(project);
        department = new Department(1,"Admin");
        Passport passport = new Passport(1,"India");


        employee = new Employee(1,"Audhi", DOB,
                department,passport,1234,"check@gmail.com",
                "12345678",false,Projects);

        department = new Department();
        department.setDepartmentName("Admin");
        department = new Department(1,"Admin");

        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        project.setEmployees(employees);
    }

    @DisplayName("JUnit test for getAllProjects method")
    @Test
    public void testDisplayAllProjectsSuccess(){
        when(projectDao.findByIsDeletedFalse()).thenReturn(List.of(project));
        List<ProjectDto> projects = projectService.getAllProject();

        assertThat(projects).isNotNull();
        assertThat(projects.size()).isEqualTo(1);
    }

    @Test
    public void testShowProjectByIdSuccess(){
        when(projectDao.findByIsDeletedFalseAndProjectId(1)).thenReturn(project);
        ProjectDto savedProject = projectService.getProjectById(1);
        assertThat(savedProject).isNotNull();
    }
    @Test
    public void testAddProjectSuccess() {
        ProjectDto requestDto = new ProjectDto(1,"teams");
        requestDto.setName("teams");
//        when(projectDao.existsByProjectName("teams")).thenReturn(false);
        when(projectDao.save(any(Project.class))).thenReturn(project);
        ProjectDto savedProjectDto = projectService.addProject(requestDto);
        assertThat(savedProjectDto.getId()).isNotNull();
    }
    @Test
    public void ObtainEmployeesByProjectIdSuccess() {
        when(projectDao.findByIsDeletedFalseAndProjectId(1)).thenReturn(project);
        List<EmployeeDto> employees = projectService.getEmployeesByProjectId(1);
        assertThat(employees.size()).isEqualTo(1);
    }

}
