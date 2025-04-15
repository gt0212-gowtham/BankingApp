package com.bofa.util;

import com.bofa.model.ServiceModel;
import com.bofa.service.ExecServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class bofakafkaconsumer {

    @Autowired
    private ExecServiceImpl execService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "bofa-service-topic", groupId = "bofa-consumer-group")
    public void consume(String message) {
        try {
            ServiceModel service = objectMapper.readValue(message, ServiceModel.class);
            execService.saveServiceWithThreadSafety(service);  // Thread-safe + duplicate check
            System.out.println("Kafka message consumed and saved: " + service.getServiceId());
        } catch (Exception e) {
            System.err.println("Error processing Kafka message: " + message);
            e.printStackTrace();
        }
    }
}

