package com.bofa.util;

import com.bofa.model.Employee;
import com.bofa.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BofaKafkaConsumer {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "Employee-Topic", groupId="Bofa-Consumer-Group")
    public void consume(String message) {
        try{
            Employee employee=objectMapper.readValue(message,Employee.class);
            employeeService.saveEmployeeWithThreadSafety(employee);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
