package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerForm;
import com.example.demo.model.CustomerModel;
import com.example.demo.service.CustomerService;
import com.example.demo.utilService.CustomerUtilService;

/**
 * Customer details controller user for having role based security on mappings.
 *
 */
@RestController
@CrossOrigin
public class CustomerDetailsController {

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerUtilService utilService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	/**
	 * This method will save customer into table.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertCustomer", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody CustomerModel user) throws Exception {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		customerService.save(user);
		return ResponseEntity.ok(user);
	}

	/**
	 * This method will fetch all customer details only if role is branch manager.
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_branchmgr')")
	@RequestMapping(value = "/customerListingPage")
	public ResponseEntity<?> customerListing() {
		List<CustomerModel> customerList = customerService.getAllCustomers();
		return ResponseEntity.ok(customerList);
	}

	/**
	 * This method will fetch customer details only if role is customer and data is
	 * read only.
	 * 
	 * @param customerId
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_customer')")
	@RequestMapping(value = "/customerDetailsPage")
	public ResponseEntity<?> customerDetailsPage(@RequestParam(name = "customer_id") Integer customerId) {
		CustomerForm form = utilService.getCustomerDetailsForm(customerId);
		form.setReadOnly(false);
		return ResponseEntity.ok(form);
	}

	/**
	 * This method will fetch customer details only if role is branch manager and
	 * data is read only.
	 * 
	 * @param customerId
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_branchmgr')")
	@RequestMapping(value = "/managerCustomerDetailsPage")
	public ResponseEntity<?> managerCustomerDetailsPage(@RequestParam(name = "customer_id") Integer customerId) {
		CustomerForm form = utilService.getCustomerDetailsForm(customerId);
		form.setReadOnly(true);
		return ResponseEntity.ok(form);
	}

	/**
	 * This method is used by account service application to consume all available
	 * customers.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/customerListServiceUrl")
	public ResponseEntity<?> customerListingServiceUrl() {
		List<CustomerModel> customerList = customerService.getAllCustomers();
		return ResponseEntity.ok(customerList);
	}
}
