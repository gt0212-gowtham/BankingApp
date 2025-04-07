package com.bofa.service;

import java.util.*;
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

    private List<ServiceModel> serviceCache = new ArrayList<>();
    private Set<Long> activeServiceIds = new HashSet<>();
    private Map<Long, ServiceModel> serviceMap = new HashMap<>();

    @Override
    public ServiceModel getService(Long serviceId)
    {
        servrepo.findById(serviceId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service Not Found"));
        return servrepo.getById(serviceId);
    }

    @Override
    public List<ServiceModel> getAllServices() 
    {
        List<ServiceModel> servicelist = servrepo.findAll();

        if (servicelist.isEmpty()) 
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Services are not found");
        }

        // Filtering out inactive services using Iterator
        Iterator<ServiceModel> iterator = servicelist.iterator();
        while (iterator.hasNext())
        {
            ServiceModel service = iterator.next();
            if (!service.isStatus()) {
                iterator.remove(); // remove inactive services
            }
        }

        // Add to cache
        serviceCache.clear();
        serviceCache.addAll(servicelist);

        // Use HashSet to store active service IDs
        for (ServiceModel service : servicelist) 
        {
            if (service.isStatus()) 
            {
                activeServiceIds.add(service.getServiceId());
            }
            serviceMap.put(service.getServiceId(), service);
        }

        // TreeSet to display unique sorted service names
        TreeSet<String> uniqueCustomerServiceNames = new TreeSet<>();
        for (ServiceModel service : servicelist)
        {
            if (service.getCustomerService() != null)
            {
                uniqueCustomerServiceNames.add(service.getCustomerService());
            }
        }

        System.out.println("Sorted unique customer service types: " + uniqueCustomerServiceNames);

        // forEach loop to show lending service names
        servicelist.forEach(service -> 
        {
            if (service.getLendingServices() != null) 
            {
                System.out.println("Lending Services for ID " + service.getServiceId() + ": " + Arrays.toString(service.getLendingServices()));
            }
        });

        return servicelist;
    }

    @Override
    public void deleteService(Long serviceId)
    {
        if (servrepo.findById(serviceId).isPresent()) 
        {
            servrepo.deleteById(serviceId);
            System.out.println("This ServiceId got deleted: " + serviceId);
        } else
        {
            System.out.println("The serviceId is not found");
        }
    }

    @Override
    public ServiceModel updateService(ServiceModel service)
    {
        System.out.println("Updating the service");
        if (service.getServiceId() == 0) 
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid service ID");
        }
        return servrepo.save(service);
    }

    @Override
    public ServiceModel saveService(ServiceModel service) {
        return servrepo.save(service);
    }
}
