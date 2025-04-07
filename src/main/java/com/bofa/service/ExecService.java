package com.bofa.service;

import com.bofa.model.ServiceModel;

import java.util.List;


public interface ExecService {
	
    public ServiceModel getService(Long serviceId);

    public List<ServiceModel> getAllServices();

    public void deleteService(Long serviceId);

    public ServiceModel updateService(ServiceModel service);

    public ServiceModel saveService(ServiceModel service);


}

/*
package com.bofa.service;

import com.bofa.model.Client;

import java.util.List;

public interface ClientService {
	
    public Client addClient(Client client);
    public Client getClient(Long clientId);
    public List<Client> getAllClients();
    public void deleteClient(Long clientId);
    public Client updateClient(Client client);
}

*/