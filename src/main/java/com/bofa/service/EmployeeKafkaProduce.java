package com.bofa.service;

import com.bofa.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeKafkaProduce {

    public EmployeeKafkaProduce() {}

    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    private final String topic="Employee-Topic";

    public void sendEmployee(Employee employee){
        try{
            ObjectMapper mapper =new ObjectMapper();
            String json= mapper.writeValueAsString(employee);
            kafkaTemplate.send(topic, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
