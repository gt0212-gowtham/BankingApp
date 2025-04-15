package com.bofa.util;

import com.bofa.model.ServiceModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class bofakafkaproducer {

    private static final String TOPIC = "bofa-service-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;



	public void sendServiceToKafka(ServiceModel service) {
		
        try {
            String jsonMessage = objectMapper.writeValueAsString(service);
            kafkaTemplate.send(TOPIC, jsonMessage);
            System.out.println("Sent to Kafka: " + jsonMessage);
        } catch (Exception e) {
            System.err.println("Failed to send service to Kafka");
            e.printStackTrace();
        }
		// TODO Auto-generated method stub
		
	}
}
