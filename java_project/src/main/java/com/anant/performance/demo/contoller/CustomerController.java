package com.anant.performance.demo.contoller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anant.performance.demo.com.anant.dao.Customer;
import com.anant.performance.demo.com.anant.dao.CustomerRepository;
import com.anant.performance.demo.com.anant.dao.KafkaSenderListener;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	KafkaSenderListener kafkaSender;

	@RequestMapping(value = "/customer", headers = "Accept=application/json", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity insertCustomer(@RequestBody Customer cust, BindingResult result) {

		ResponseEntity re = new ResponseEntity(HttpStatus.CREATED);
		customerRepository.save(cust);
		System.out.println("Thread Id 1->>>" + Thread.currentThread().getId());
		System.out.println("Thread Id 1->>>" + Thread.currentThread().getId());
		System.out.println("Thread Id 1->>>" + Thread.currentThread().getId());
		System.out.println("Thread Id 1->>>" + Thread.currentThread().getId());
		System.out.println("Thread Id 1->>>" + Thread.currentThread().getId());
		kafkaSender.send("test2", "Anant");
		return re;
	}

	@RequestMapping(value = "/customers", headers = "Accept=application/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> getCustomers() {
		List<Customer> result = new LinkedList<Customer>();
		Iterable<Customer> customers = customerRepository.findAll();
		Iterator<Customer> custItr = customers.iterator();

		while (custItr.hasNext()) {
			Customer cust = custItr.next();
			result.add(cust);
		}

		return result;
	}

	@RequestMapping(value = "/customer/{id}", headers = "Accept=application/json", method = RequestMethod.GET)
	@ResponseBody
	public Customer getCustomer(@PathVariable long id) {
		List<Customer> result = new LinkedList<Customer>();
		Customer customer = customerRepository.findOne(id);

		return customer;
	}
}
