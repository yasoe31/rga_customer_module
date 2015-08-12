package com.rga.customermodule.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

import com.rga.customermodule.model.Customer;
import com.rga.customermodule.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;

	private static final Logger LOGGER = (Logger) LoggerFactory
			.getLogger(CustomerController.class);

	@RequestMapping(value = "/listCustomer", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> listCustomer() {

		LOGGER.debug("Listing All Customer");
		return (List<Customer>) customerService.findAll();

	}

	@RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
	@ResponseBody
	public void registerCustomer(@RequestBody Customer customer) {

		LOGGER.debug("Registering A Customer");
		customerService.save(customer);
	}
	
	@RequestMapping(value="/getACustomer/{id}",method=RequestMethod.GET)
	@ResponseBody
	private Customer getACustomer(@PathVariable String id) {
		
		return customerService.findOne(Integer.parseInt(id));
	}

}
