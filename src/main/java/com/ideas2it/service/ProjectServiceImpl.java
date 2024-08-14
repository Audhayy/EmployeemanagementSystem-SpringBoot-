package com.ideas2it.service;

import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.mapper.ProjectMapper;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *<p>
 *This class acts as a Interface for the ProjectServiceImpl.
 *the methods in the Service class is called for abstraction purpose
 *</p>
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {

        this.projectDao = projectDao;
    }


    public ProjectDto addProject(ProjectDto projectDto) {
        Project convertProject = ProjectMapper.convertDtoToEntity(projectDto);
        logger.info("Project has been created successfully");
        return ProjectMapper.convertEntityToDto(projectDao.save(convertProject));

    }

    public List<ProjectDto> getAllProject() {
        List<ProjectDto> result = new ArrayList<>();
        List<Project> projects = projectDao.findByIsDeletedFalse();
        if(projects.isEmpty()) {
            logger.warn("ProjectList is empty");
        } else {
            for (Project project : projects) {
                result.add(ProjectMapper.convertEntityToDto(project));
            }
        }
        return result;
    }

    public void removeProject(int id) {
        Project project = projectDao.findByIsDeletedFalseAndProjectId(id);
        project.setDeleted(true);
        projectDao.save(project);
        logger.info("project has been deleted with id ..{}",id);

    }

    public ProjectDto updateProject(int id, ProjectDto projectDto)  {
        Project convertProject = ProjectMapper.convertDtoToEntity(projectDto);
        Project existingProject =  projectDao.findByIsDeletedFalseAndProjectId(id);
        existingProject.setProjectName(convertProject.getProjectName());
        logger.info("project has been updated with id ..{}",id);
        return ProjectMapper.convertEntityToDto(projectDao.save(existingProject));
    }

    public ProjectDto getProjectById(int id) {
        Project project = projectDao.findByIsDeletedFalseAndProjectId(id);
        return ProjectMapper.convertEntityToDto(project);
    }
    public List<EmployeeDto> getEmployeesByProjectId(int projectId) {
        Project project = projectDao.findByIsDeletedFalseAndProjectId(projectId);
        List<EmployeeDto> existingEmployee = new ArrayList<>();
        for(Employee employee :project.getEmployees()) {
            if(!employee.isDeleted()) {
                existingEmployee.add(EmployeeMapper.convertToDto(employee));
            }
        }
        return existingEmployee;

    }
}



