package com.rga.customermodule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rga.customermodule.dao.CustomerDao;
import com.rga.customermodule.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public CustomerService() {

	}

	public CustomerService(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public String saveCustomer(Customer customer) {
		
		String temp = null;

		if (customer.getName() == null || customer.getName().isEmpty()) {
			temp= "Name should not be empty";
		}

		if (customer.getPhNo() == null || customer.getPhNo().isEmpty()) {
			return "Phno should not be empty";
		}

		if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
			return "Email should not be empty";
		}
		
		if (temp ==null) {
			try {
				customerDao.save(customer);
			} catch (Exception e) {
				return "could not save customer";
			}
		}
		return temp;

	}

	/*public void saveCustomer(Customer customer) {
		customerDao.save(customer);
	}*/

	public List<Customer> listCustomer() {
		return (List<Customer>) customerDao.findAll();
	}

	public Customer getACustomer(int id) {
		return customerDao.findOne(id);
	}

	public void deleteCustomer(int id) {
		customerDao.delete(id);
	}

}
