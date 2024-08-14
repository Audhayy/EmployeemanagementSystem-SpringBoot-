package com.ideas2it.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
  *This is a class created to associate with employee
  *and has data members like passport name,passport id.
  *
  *@author Audhithiyah
  *</p>
  */
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "passport") 
public class Passport {  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private int id;

    @Column(name = "passport_number")
    private int passportNumber;

    @Column(name ="country_name")
    private String countryName;

    public Passport() {}
    public Passport(int passportNumber, String countryName) {
        this.passportNumber = passportNumber;
        this.countryName = countryName;
    }
}


