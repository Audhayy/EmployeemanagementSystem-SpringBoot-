package com.ideas2it.controller;


import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * This class is the Controller for project get all the Information's
 * about the project and also various methods like
 * Create project Records
 * Display the project record(s)
 * Update the project Record
 * Delete the project Record in the Repository
 * </p>
 *
 * @author Audhithiyah
 */
@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * <p>
     *This method is used to create a department into the repository
     * </p>
     * @return {@link ProjectDto}
     */
    @PostMapping
    private ProjectDto addProject(@RequestBody ProjectDto projectDto) {

        return projectService.addProject(projectDto);
    }
    /**
     * <p>
     *This method is used to show all projects in the repository
     * </p>
     */
    @GetMapping
    public List<ProjectDto> getAllProjects() {

      return projectService.getAllProject();

    }
    /**
     * <p>
     *This method is used to display a specific project in the repository
     * @param projectId - unique identifier of the project
     * </p>
     */
    @GetMapping("/{id}")
    public ProjectDto displayProject(@PathVariable("id") int projectId) {
        return projectService.getProjectById(projectId);
    }
    /**
     * <p>
     *This method is used to update a department into the repository
     * @param id- unique identifier of project
     * </p>
     */
    @PutMapping("{id}")
    public ProjectDto updateProject(@PathVariable("id")int projectId,@RequestBody ProjectDto projectDto) {
        return projectService.updateProject(projectId, projectDto);
    }
    /**
     * <p>
     *This method is used to delete a department into the repository
     * </p>
     */
    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id")int projectId) {

        projectService.removeProject(projectId);
    }
    public ResponseEntity<List<EmployeeDto>>displayEmployeeByProject(@PathVariable int projectId) {
        List<EmployeeDto> employees = projectService.getEmployeesByProjectId(projectId);
        return new ResponseEntity<>(employees, HttpStatus.OK);

    }

}



