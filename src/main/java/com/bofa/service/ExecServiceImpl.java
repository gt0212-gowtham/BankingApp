package com.bofa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bofa.exception.ServiceAlreadyExistsException;
import com.bofa.exception.ServiceNotFoundException;
import com.bofa.model.ServiceModel;
import com.bofa.repository.ServiceRepository;
import com.bofa.util.statusComparator;
import com.bofa.util.IdComparator;

@Service
public class ExecServiceImpl implements ExecService {

    @Autowired
    private ServiceRepository servrepo;

    private List<ServiceModel> serviceCache = new ArrayList<>();
    private Set<Long> activeServiceIds = new HashSet<>();
    private Map<Long, ServiceModel> serviceMap = new HashMap<>();
    
    // Thread safety additions
    private final ReentrantLock lock = new ReentrantLock();
    private final Set<Long> savedIds = Collections.synchronizedSet(new HashSet<>());
    

    @Override
    public ServiceModel getService(Long serviceId)
    {
        return servrepo.findById(serviceId)
            .orElseThrow(() -> new ServiceNotFoundException("Service with ID " + serviceId + " not found"));
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
        
        List<ServiceModel> list = servrepo.findAll();
        Collections.sort(list); // Uses Comparable
//        
        serviceCache.sort(new IdComparator());
        serviceCache.sort(new statusComparator());
//        

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
        if (servrepo.existsById(service.getServiceId())) {
            throw new ServiceAlreadyExistsException("Service with ID " + service.getServiceId() + " already exists");
        }
        return servrepo.save(service);
    }


    
    public void processAsyncSave(ServiceModel service)
    {
    	saveServiceWithThreadSafety(service);
    }
    
    //Thread-safe save logic
    public void saveServiceWithThreadSafety(ServiceModel service) {
        lock.lock();
        try {
            if (!savedIds.contains(service.getServiceId())) {
                savedIds.add(service.getServiceId());
                servrepo.save(service);
                System.out.println("Saved service: " + service.getServiceId());
            } else {
                System.out.println("Duplicate detected: " + service.getServiceId());
            }
        } finally {
            lock.unlock();
        }
    }
   
}


