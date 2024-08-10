package com.ideas2it.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
  *This is a class created to associate with employee
  *and has data members like passport name,passport id.
  *
  *@author Audhithiyah
  */

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

    public Passport(int passportNumber,String countryName) {
        this.passportNumber = passportNumber;
        this.countryName = countryName;
    }
	
    public String getcountryName() {
        return countryName;
    }

    public void setcountryName(String countryName) {
        this.countryName = countryName;
    }
    public int getpassportNumber() {
        return passportNumber;
    }

    public void setpassportNumber(int passportNumber) {
       this.passportNumber = passportNumber;
    }

}


