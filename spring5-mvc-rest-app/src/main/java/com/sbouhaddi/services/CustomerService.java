/**
 * 
 */
package com.sbouhaddi.services;

import java.util.List;

import com.sbouhaddi.api.v1.model.CustomerDTO;

/**
 * @author bouhaddisabri
 *
 */
public interface CustomerService {

	List<CustomerDTO> getAllCustomers();

	CustomerDTO getCustomerById(Long id);

	CustomerDTO createNewCustomer(CustomerDTO customerDTO);

	CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
	
	CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
	
	void deleteCustomerById(Long id);
}
