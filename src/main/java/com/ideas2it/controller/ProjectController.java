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
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("/add")
    private ProjectDto addProject(@RequestBody ProjectDto projectDto) {
        return projectService.addProject(projectDto);
    }
    @GetMapping("/get")
    public List<ProjectDto> getAllProjects() {

      return projectService.getAllProject();

    }
    @GetMapping("/{id}")
    public ProjectDto displayProject(@PathVariable("id") int projectId) {
        return projectService.getProjectById(projectId);
    }

    @PutMapping("/update/{id}")
    public ProjectDto updateProject(@PathVariable("id")int projectId,@RequestBody ProjectDto projectDto) {
        return projectService.updateProject(projectId, projectDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id")int projectId) {

        projectService.removeProject(projectId);
    }
    public ResponseEntity<List<EmployeeDto>>displayEmployeeByProject(@PathVariable int projectId) {
        List<EmployeeDto> employees = projectService.getEmployeesByProjectId(projectId);
        return new ResponseEntity<>(employees, HttpStatus.OK);

    }

}



