package com.bofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bofa.model.ServiceModel;
import com.bofa.repository.ServiceRepository;

@Service
public class ExecServiceImpl implements ExecService {

    @Autowired
    private ServiceRepository servrepo;

    @Override
    public ServiceModel getService(Long serviceId) {
        servrepo.findById(serviceId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service Not Found"));
        return servrepo.getById(serviceId);
    }

    @Override
    public List<ServiceModel> getAllServices() {
        List<ServiceModel> servicelist = servrepo.findAll();
        if (servicelist.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Services are not found");
        }
        servicelist.forEach(service ->
            System.out.println("Service details are as follows: " + service)
        );
        return servicelist;
    }

    @Override
    public void deleteService(Long serviceId) {
        if (servrepo.findById(serviceId).isPresent()) {
            servrepo.deleteById(serviceId);
            System.out.println("This ServiceId got deleted: " + serviceId);
        } else {
            System.out.println("The serviceId is not found");
        }
    }

    @Override
    public ServiceModel updateService(ServiceModel service) {
        System.out.println("Updating the service");
        if (service.getServiceId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid service ID");
        }
        return servrepo.save(service);
    }

    @Override
    public ServiceModel saveService(ServiceModel service) {
        return servrepo.save(service);
    }
}
