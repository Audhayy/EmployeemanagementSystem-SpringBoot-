package com.ideas2it.project.service;

import java.util.List;


import com.ideas2it.model.Department;
import com.ideas2it.project.dto.ProjectDto;
import com.ideas2it.model.Project;
import com.ideas2it.project.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 *<p>
 *This class acts as a Interface for the ProjectServiceImpl.
 *the methods in the Service class is called for abstraction purpose
 *</p>
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }


    public Project addProject(Project project) {
        return projectDao.save(project);

    }

    public List<Project> displayAllProjects() {
        return (List<Project>) projectDao.findAll();


    }
    public void removeProject(int id) {
        projectDao.deleteById(id);
    }

    public Project updateProject(int id, Project project)  {
        Project presentProject =  projectDao.findById(id).orElse(null);
        presentProject.setProjectName(project.getProjectName());
        return projectDao.save(presentProject);
    }

    public Project displayProject(int id) {
        Project project = projectDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Project not found with ID:" + id)));
        return project;
    }

}



