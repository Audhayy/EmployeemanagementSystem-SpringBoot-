package com.ideas2it.service;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    public ProjectDto addProject(ProjectDto projectDto);

    public List<ProjectDto> getAllProject();

    public void removeProject(int id) ;

    public ProjectDto updateProject(int id, ProjectDto projectDto);

    public ProjectDto getProjectById(int id);

    public List<EmployeeDto> getEmployeesByProjectId(int projectId);


}
