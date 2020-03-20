/**
 * 
 */
package com.sbouhaddi.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sbouhaddi.api.v1.model.CustomerDTO;
import com.sbouhaddi.domain.Customer;

/**
 * @author bouhaddisabri
 *
 */
public class CustomerMapperTest {

	public static final String FIRSTNAME = "Sabri";
	public static final String LASTNAME = "Sabri";
	public static final long ID = 1L;

	CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	@Test
	public void customerToCustomerDTO() throws Exception {
		// given
		Customer customer = new Customer();
		customer.setFirstName(FIRSTNAME);
		customer.setLastName(LASTNAME);

		// when
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

		// then
		assertEquals(FIRSTNAME, customerDTO.getFirstName());
		assertEquals(LASTNAME, customerDTO.getLastName());

	}
}
