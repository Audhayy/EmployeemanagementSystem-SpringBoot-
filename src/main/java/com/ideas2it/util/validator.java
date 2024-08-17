package com.ideas2it.util;

import com.ideas2it.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;

public class validator {
    @Autowired
    Employee employee;
    public  static String ageCalculator(LocalDate employeeDOB) {
        return Period.between(employeeDOB, LocalDate.now()).getYears() + "y"
                + Period.between(employeeDOB, LocalDate.now()).getMonths() + "m";
    }

}
