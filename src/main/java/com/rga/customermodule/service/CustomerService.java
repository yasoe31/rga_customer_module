package com.rga.customermodule.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.rga.customermodule.model.Customer;



@Service
public interface CustomerService extends CrudRepository<Customer, Integer> {

}
