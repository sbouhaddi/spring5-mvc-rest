/**
 * 
 */
package com.sbouhaddi.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sbouhaddi.api.v1.model.CustomerDTO;
import com.sbouhaddi.domain.Customer;

/**
 * @author bouhaddisabri
 *
 */
@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	CustomerDTO customerToCustomerDTO(Customer customer);
	
	Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
