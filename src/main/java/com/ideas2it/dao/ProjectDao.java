package com.ideas2it.dao;

import com.ideas2it.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends CrudRepository<Project, Integer> {
    List<Project> findByIsDeletedFalse();
    Project findByIsDeletedFalseAndProjectId(int projectId);
}
