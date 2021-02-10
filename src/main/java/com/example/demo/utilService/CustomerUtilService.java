package com.example.demo.utilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerForm;
import com.example.demo.model.CustomerModel;
import com.example.demo.service.CustomerService;

/**
 * Util service class for populating customer details into form.
 *
 */
@Service
public class CustomerUtilService {

	@Autowired
	CustomerService customerService;

	/**
	 * get form to send to view page
	 * 
	 * @param customerId
	 * @return
	 */
	public CustomerForm getCustomerDetailsForm(Integer customerId) {
		if (customerId == null) {
			return new CustomerForm(null, null, null, null, null, null);
		}
		CustomerModel customerModel = customerService.findByCustomerId(customerId);
		return new CustomerForm(customerModel.getUserId(), customerModel.getRole().getRoleId(),
				customerModel.getUserName(), customerModel.getDateOfBirth().toString(), customerModel.getGender(),
				customerModel.getPhoneNumber());

	}
}
