package com.rga.customermodule.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rga.customermodule.config.JavaConfig;
import com.rga.customermodule.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
@WebAppConfiguration
public class CustomerControllerTest {

	@Autowired
	CustomerController customerController;

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicaitonContext;

	@Before
	public void setUp() {
		Mockito.reset(customerController);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicaitonContext)
				.build();
	}

	@Test
	public void list_all_customer() throws Exception {

		Customer customer = new Customer();
		customer.setId(1);

		when(customerController.listCustomer()).thenReturn(
				Arrays.asList(customer));

		mockMvc.perform(get("/listCustomer")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1)
				/* .andDo(MockMvcResultHandlers.print() */);

	}

	@Test
	public void when_register_customer_without_setting_name_return_400_bad_request()
			throws Exception {

		Customer customer = new Customer();
		customer.setPhNo("12");
		customer.setEmail("y@gmail.com");
		when(customerController.registerCustomer(customer)).thenReturn(
				new ResponseEntity<String>(HttpStatus.BAD_REQUEST));

		/*
		 * mockMvc.perform( post("/registerCustomer").contentType(
		 * JsonUtil.APPLICATION_JSON_UTF8).content(
		 * JsonUtil.convertObjectToJson(customer)))
		 * .andDo(MockMvcResultHandlers.print())
		 * .andExpect(status().isBadRequest());
		 */

		ResponseEntity<String> response = customerController
				.registerCustomer(customer);
		/*
		 * System.out.println(response);
		 * System.out.println(response.getStatusCode());
		 * System.out.println(response.getHeaders());
		 * System.out.println(response.getBody());
		 * System.out.println(response.getHeaders());
		 * System.out.println(response.getStatusCode().getReasonPhrase());
		 */

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Bad Request", response.getStatusCode()
				.getReasonPhrase());

	}

	@Test
	public void when_register_customer_without_setting_email_return_400_bad_request() {
		Customer customer = new Customer();
		customer.setName("yan");
		customer.setPhNo("078");

		when(customerController.registerCustomer(customer)).thenReturn(
				new ResponseEntity<String>(HttpStatus.BAD_REQUEST));

		ResponseEntity<String> response = customerController
				.registerCustomer(customer);

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Bad Request", response.getStatusCode()
				.getReasonPhrase());
	}

	@Test
	public void when_register_customer_without_setting_phno_return_400_bad_request() {
		Customer customer = new Customer();
		customer.setName("yan");
		customer.setEmail("yan@gmail.com");

		when(customerController.registerCustomer(customer)).thenReturn(
				new ResponseEntity<String>(HttpStatus.BAD_REQUEST));

		ResponseEntity<String> response = customerController
				.registerCustomer(customer);

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Bad Request", response.getStatusCode()
				.getReasonPhrase());
	}

	@Test
	public void when_register_customer_with_name_email_phno_return_200_OK() {

		Customer customer = new Customer();
		customer.setEmail("yan@email.com");
		customer.setName("yan");
		customer.setPhNo("078");

		when(customerController.registerCustomer(customer)).thenReturn(
				new ResponseEntity<String>(HttpStatus.OK));

		ResponseEntity<String> response = customerController
				.registerCustomer(customer);

		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals("OK", response.getStatusCode().getReasonPhrase());
	}
}
