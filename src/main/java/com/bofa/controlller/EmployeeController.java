package com.bofa.controlller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.bofa.model.Employee;
import com.bofa.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gmail")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;
    @PostMapping("/saveEmployee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/getEmployeeById/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Long employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.ok(employeeService.getSortedEmployees());
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployeeWithThreadSafety(employee);
        return ResponseEntity.status(201).body("saved");
    }

}
