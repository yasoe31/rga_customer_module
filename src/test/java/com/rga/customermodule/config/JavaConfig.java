package com.rga.customermodule.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rga.customermodule.controller.CustomerController;
import com.rga.customermodule.dao.CustomerDao;
import com.rga.customermodule.model.Customer;
import com.rga.customermodule.service.CustomerService;

@Configuration
@EnableWebMvc
public class JavaConfig extends WebMvcConfigurerAdapter {

	@Bean
	CustomerController customerController() {
		return Mockito.mock(CustomerController.class);
	}
	
	@Bean
	CustomerService customerService(){
		return Mockito.mock(CustomerService.class);
	}
	
	@Bean
	Customer customer(){
		return Mockito.mock(Customer.class);
	}
	
	@Bean
	CustomerDao customerDao(){
		return Mockito.mock(CustomerDao.class);
	}
	
	

}
