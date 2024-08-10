package com.ideas2it.project.controller;


import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.model.Department;
import com.ideas2it.model.Project;
import com.ideas2it.project.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideas2it.project.service.ProjectService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public ProjectDto convertToDto(Project project) {
        return new ProjectDto(project.getProjectId(),project.getProjectName() );
    }

    public Project convertToEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setProjectId(projectDto.getId());
        project.setProjectName(projectDto.getName());
        return project;
    }

    @PostMapping("/add")
    private ProjectDto addProject(@RequestBody ProjectDto projectDto) {
        return convertToDto(projectService.addProject(convertToEntity(projectDto)));
    }
    @GetMapping("/get")
    public List<ProjectDto> getAllProjects() {
        List<ProjectDto> result = new ArrayList<>();
        List<Project> Projects = projectService.displayAllProjects();
        for (Project project : Projects) {
            System.out.println(project);
            if (!project.isDeleted()){
                result.add(convertToDto(project));
            }
        }
        return result;
    }
    @GetMapping("/{id}")
    public ProjectDto displayProject(@PathVariable("id") int projectId) {
        return convertToDto(projectService.displayProject(projectId));
    }

    @PutMapping("/update/{id}")
    public ProjectDto updateProject(@PathVariable("id")int projectId,@RequestBody ProjectDto projectDto) {
        return convertToDto(projectService.updateProject(projectId, convertToEntity(projectDto)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id")int projectId) {
        projectService.removeProject(projectId);
    }

}



