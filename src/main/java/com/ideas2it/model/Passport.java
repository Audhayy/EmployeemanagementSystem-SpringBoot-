package com.ideas2it.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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

    public Passport(int passportNumber, String countryName) {
        this.passportNumber = passportNumber;
        this.countryName = countryName;
    }
}


