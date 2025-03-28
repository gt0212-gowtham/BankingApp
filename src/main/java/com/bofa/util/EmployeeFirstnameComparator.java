package com.bofa.util;

import com.bofa.model.Employee;

import java.util.Comparator;

public class EmployeeFirstnameComparator implements Comparator<Employee> {
    public int compare(Employee o1, Employee o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}

