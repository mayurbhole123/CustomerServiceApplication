package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.model.CustomerModel;

/**
 * Interface having abstract method which need to implements in customer dao
 * implementation class.
 *
 */
public interface CustomerDao {

	public void save(CustomerModel customer);

	public CustomerModel findByCustomerId(int customerId);

	public ArrayList<CustomerModel> getAllCustomers();

}