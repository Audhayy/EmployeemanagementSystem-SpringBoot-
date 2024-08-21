package com.ideas2it.service;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 *Class to implement interface for the project Service
 *this interface provides methods for managing project records.
 */
public interface ProjectService {
    /**
     *Method implemented in serviceImpl for creating project records.
     *@param projectDto - object of the employee to be added
     */
    public ProjectDto addProject(ProjectDto projectDto);

    /**
     *Method implemented in ServiceImpl for showing all the projects available.
     */
    public List<ProjectDto> getAllProject();

    /**
     *Method implemented in ServiceImpl for showing the project corresponding to the id.
     *@param id - id of the project the employee is in
     */
    public void removeProject(int id) ;

    /**
     * Method implemented in ServiceImpl to get update the employee details
     * that has been previously given.
     *
     * @param projectDto - object of the class ProjectDto
     * @param id - unique identifier of project
     */
    public ProjectDto updateProject(int id, ProjectDto projectDto);

    /**
     *Method implemented in ServiceImpl to check if the project id given by the employee
     *matches with the project id in the employee.
     *@param id - unique identifier of the project
     */
    public ProjectDto getProjectById(int id);

    public List<EmployeeDto> getEmployeesByProjectId(int projectId);


}
