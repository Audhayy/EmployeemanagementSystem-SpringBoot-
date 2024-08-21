package com.ideas2it.dao;

import com.ideas2it.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *CRUD operations for projects is performed in this class
 */
@Repository
public interface ProjectDao extends CrudRepository<Project, Integer> {
    /**
     * <p>
     *This method is used to display projects that are found to be soft deleted
     * </p>
     */
    List<Project> findByIsDeletedFalse();
    /**
     * <p>
     *This method is returns a single project that is soft deleted
     * </p>
     */
    Project findByIsDeletedFalseAndProjectId(int projectId);
}
