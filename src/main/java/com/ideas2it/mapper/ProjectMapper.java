package com.ideas2it.mapper;

import com.ideas2it.dto.ProjectDto;
import com.ideas2it.model.Project;

public class ProjectMapper {
    public static ProjectDto convertEntityToDto(Project project) {
        return new ProjectDto(project.getProjectId(),project.getProjectName() );
    }

    public static Project convertDtoToEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setProjectId(projectDto.getId());
        project.setProjectName(projectDto.getName());
        return project;
    }

}
