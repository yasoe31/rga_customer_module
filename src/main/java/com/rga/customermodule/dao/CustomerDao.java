package com.rga.customermodule.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rga.customermodule.model.Customer;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {

}