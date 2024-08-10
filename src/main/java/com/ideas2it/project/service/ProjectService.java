package com.ideas2it.project.service;

import com.ideas2it.model.Project;
import com.ideas2it.project.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    public Project addProject(Project project);

    public List<Project> displayAllProjects();

    public void removeProject(int id);

    public Project updateProject(int id, Project project);

    public Project displayProject(int id);


}
