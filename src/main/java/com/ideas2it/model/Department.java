package com.ideas2it.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
  *<p>
  *This is a class created to associate with employee and 
  *and has data members like department name,department id.
  *
  *@author Audhithiyah
  *</p>
  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int departmentId;

    @Column(name ="department_name")
    private String departmentName;
    @JsonIgnore
    @OneToMany(mappedBy ="department", fetch = FetchType.EAGER)
    
    private Set<Employee> employees;

    private boolean isDeleted = false;

}


