package com.bofa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bofa.model.ServiceModel;
import com.bofa.service.ExecService;


@RestController
@RequestMapping("/api/services")
public class ServiceController {
	
	@Autowired
	ExecService execserviceimpl;
	
	@GetMapping("/{serviceId}")
	public ServiceModel getService(@PathVariable Long serviceId)
	{
		return execserviceimpl.getService(serviceId);
	}
	
	@GetMapping()
	public List<ServiceModel> getAllServices()
	{
		return execserviceimpl.getAllServices();
	}
	
	@DeleteMapping("/delete/{serviceId}")
	 public void deleteService(@PathVariable Long serviceId)
	 {
		execserviceimpl.deleteService(serviceId);
	 }
	
	
	@PostMapping("/add")
	 public ServiceModel saveService(@RequestBody ServiceModel service)
	 {
		return execserviceimpl.saveService(service);
		
	 }
	
	
	@PutMapping("/update/{serviceId}")
	 public ServiceModel updateService(@PathVariable Long serviceId,@RequestBody ServiceModel service)
	 {
		 if(service.getServiceId()!=serviceId)
		 {
			 throw new RuntimeException("SercieId is not matched or not present");
		 }
		 
		 return execserviceimpl.updateService(service);
	 }
}

