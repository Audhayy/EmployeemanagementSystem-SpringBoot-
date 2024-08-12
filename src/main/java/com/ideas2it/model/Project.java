package com.ideas2it.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;



/**
 *<p>
 *This is a class created to associate with employee and 
 *projects and has data members like project name,project id.
 *
 *@author Audhithiyah
 *</p>
 */
@Entity
@Table(name = "project")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int projectId;

    @Column(name ="name")
    private String projectName;

    @ManyToMany(mappedBy = "projectList",
            fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Employee> employees;

    @Column
    private boolean isDeleted;

}


