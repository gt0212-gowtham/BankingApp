package com.bofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bofa.model.ServiceModel;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Long>{

}
