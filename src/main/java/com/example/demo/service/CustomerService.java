package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.model.CustomerModel;

/**
 * Interface having abstract method which will be implemented by service
 * implementation class.
 *
 */
public interface CustomerService {
	public void save(CustomerModel customer);

	public CustomerModel findByCustomerId(int customerId);

	public ArrayList<CustomerModel> getAllCustomers();
}
