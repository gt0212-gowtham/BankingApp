package com.bofa.util;

import com.bofa.model.Employee;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {
    public int compare(Employee o1, Employee o2) {
        return Long.compare(o1.getSalary(),o2.getSalary());
    }
}
