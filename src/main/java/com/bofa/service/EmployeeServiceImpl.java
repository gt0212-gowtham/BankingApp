package com.bofa.service;


import com.bofa.model.Employee;
import com.bofa.repository.EmployeeRepository;
import com.bofa.util.EmployeeFirstnameComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository EmpRepo;
    private List<Employee> employeeCache=new ArrayList<>();
    private Set<Long> highsalariedempId=new HashSet<>();
    private Map<Long,Employee> employeeMap=new HashMap<>();

    public EmployeeServiceImpl(EmployeeRepository empRepo) {
        EmpRepo = empRepo;
    }

    @Override
    public Employee getEmployee(Long employeeId){
        return EmpRepo.findById(employeeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }
    @Override
    public List<Employee> getAllEmployees(){
        List<Employee> emplist=new ArrayList();
        emplist=EmpRepo.findAll();
        if(emplist.size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found");
        }

        //using arraylist collect all the employee who has salary not less than 2000
        Iterator<Employee> iterator=emplist.iterator();
        while(iterator.hasNext()){
            Employee emp=iterator.next();
            if(emp.getSalary()<2000){
                iterator.remove();
            }
            employeeCache.addAll(emplist);

            //to print unquie ids of employees from emplist
            Set<Employee> uniqueData=new HashSet<>(emplist);
            Iterator<Employee> iterator1=uniqueData.iterator();
            while(iterator1.hasNext()){
                Employee emp1=iterator1.next();
                System.out.println(emp1.getEmployeeId());
            }
      //using map print the emp who has salary greater than 3000
            for(Employee employee : emplist){
                employeeMap.put(employee.getEmployeeId(), employee);
                if(employee.getSalary()>3000){
                    highsalariedempId.add(employee.getEmployeeId());
                }
            }
        }
        emplist.forEach(employee -> System.out.println(employee));
        return emplist;

    }
    @Override
    public void deleteEmployee(Long employeeId){
        EmpRepo.deleteById(employeeId);
    }
    @Override
    public Employee updateCustomer(Employee employee){
        if(employee.getFirstName()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FirstName is required");
        }
        employeeCache.sort(new EmployeeFirstnameComparator());
        if(employee.getSalary()<0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salary must be greater than 0");
        }
        switch(employee.getFirstName()){
            case "alex": System.out.println();
            case "bob": System.out.println();
        }
        return EmpRepo.save(employee);
    }

    @Override
    public Employee saveEmployee(Employee employee){
        try {
            return EmpRepo.save(employee);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving employee", e);
        }
    }


}
