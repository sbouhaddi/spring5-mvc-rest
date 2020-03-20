/**
 * 
 */
package com.sbouhaddi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sbouhaddi.api.v1.mapper.CustomerMapper;
import com.sbouhaddi.api.v1.model.CustomerDTO;
import com.sbouhaddi.controllers.v1.CustomerController;
import com.sbouhaddi.domain.Customer;
import com.sbouhaddi.repositories.CustomerRepository;

/**
 * @author bouhaddisabri
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		super();
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll().stream().map(customer -> {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
			customerDTO.setCustomerUrl(getBaseUrl(customer.getId()));
			return customerDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {

		return customerRepository.findById(id).map(customer -> {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
			customerDTO.setCustomerUrl(getBaseUrl(customer.getId()));
			return customerDTO;
		}).orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);

		Customer savedCustomer = customerRepository.save(customer);

		CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

		returnDto.setCustomerUrl(getBaseUrl(savedCustomer.getId()));

		return returnDto;
	}

	private CustomerDTO saveAndReturnDTO(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);

		CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

		returnDto.setCustomerUrl(getBaseUrl(savedCustomer.getId()));

		return returnDto;
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		customer.setId(id);

		return saveAndReturnDTO(customer);
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer -> {

			if (customerDTO.getFirstName() != null) {
				customer.setFirstName(customerDTO.getFirstName());
			}

			if (customerDTO.getLastName() != null) {
				customer.setLastName(customerDTO.getLastName());
			}

			CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
			returnDto.setCustomerUrl(getBaseUrl(customer.getId()));
			return returnDto;

		}).orElseThrow(ResourceNotFoundException::new); 
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}

	private String getBaseUrl(Long id) {
		return CustomerController.BASE_URL + "/" + id;
	}
}
