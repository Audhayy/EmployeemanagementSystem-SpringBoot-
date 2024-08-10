package com.ideas2it.project.dao;

import com.ideas2it.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao extends CrudRepository<Project, Integer> {
}
