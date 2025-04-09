package com.bofa.util;

import com.bofa.model.Client;
import com.bofa.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClientKafkaConsumer {

    @Autowired private ObjectMapper objectMapper;
    @Autowired private ClientService clientService;

    @KafkaListener(topics = "client-topic", groupId = "bofa-consumer-group")
    public void consume(String message) {
        try {
            Client client = objectMapper.readValue(message, Client.class);
            clientService.addClient(client);
            System.out.println("Consumed client: " + client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
