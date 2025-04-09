package com.bofa.controller;

import com.bofa.model.Client;
import com.bofa.service.ClientService;
import com.bofa.service.ClientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientServiceImpl;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String KAFKA_TOPIC = "client-topic";


    @PostMapping("/addKafka")
    @ResponseStatus(HttpStatus.OK)
    public String addClientKafka(@RequestBody Client client) {
        try{
            String message = objectMapper.writeValueAsString(client);
            kafkaTemplate.send(KAFKA_TOPIC, message);
            return "Client added to Kafka topic";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add client to Kafka topic";
        }
    }


    @PostMapping("/add")
    public Client addClient(@RequestBody Client client) {
        return clientServiceImpl.addClient(client).join();
    }

    @GetMapping()
    public List<Client> getAllClients() {
        return clientServiceImpl.getAllClients().join();
    }

    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable Long clientId) {
        return clientServiceImpl.getClient(clientId).join();
    }

    @PutMapping("/update/{clientId}")
    public Client updateClient(@PathVariable Long clientId,  @RequestBody Client client) {
        if (!client.getClientId().equals(clientId)) {
            throw new RuntimeException("Client ID does not match");
        }
        return clientServiceImpl.updateClient(client).join();
    }

    @DeleteMapping("/delete/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientServiceImpl.deleteClient(clientId).join();
    }


}
