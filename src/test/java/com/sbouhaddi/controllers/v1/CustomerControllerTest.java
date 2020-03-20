/**
 * 
 */
package com.sbouhaddi.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sbouhaddi.api.v1.model.CustomerDTO;
import com.sbouhaddi.controllers.RestResponseEntityExceptionHandler;
import com.sbouhaddi.services.CustomerService;
import com.sbouhaddi.services.ResourceNotFoundException;

/**
 * @author bouhaddisabri
 *
 */
public class CustomerControllerTest extends AbstractRestControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(customerController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	public void testListCustomers() throws Exception {

		// given
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstName("Michale");
		customer1.setLastName("Weston");
		customer1.setCustomerUrl(CustomerController.BASE_URL + "/1");

		CustomerDTO customer2 = new CustomerDTO();
		customer2.setFirstName("Sam");
		customer2.setLastName("Axe");
		customer2.setCustomerUrl(CustomerController.BASE_URL + "/2");

		when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

		mockMvc.perform(get(CustomerController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.customers", hasSize(2)));
	}

	@Test
	public void testGetCustomerById() throws Exception {

		// given
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setFirstName("Michale");
		customer1.setLastName("Weston");
		customer1.setCustomerUrl(CustomerController.BASE_URL + "/1");

		when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

		// when
		mockMvc.perform(get(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", equalTo("Michale")));
	}

	@Test
	public void createNewCustomer() throws Exception {
		// given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

		when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

		// when/then
		mockMvc.perform(post(CustomerController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", equalTo("Fred")))
				.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		// given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

		when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

		// when/then
		mockMvc.perform(put(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo("Fred")))
				.andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
				.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
	}

	@Test
	public void testPatchCustomer() throws Exception {
		// given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Sam");
		customer.setLastName("Flintstone");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

		when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

		// when/then
		mockMvc.perform(patch(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo("Sam")))
				.andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
				.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
	}

	@Test
	public void testDeleteCustomer() throws Exception {

		mockMvc.perform(delete(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		verify(customerService).deleteCustomerById(anyLong());
	}

	@Test
	public void testNotFoundException() throws Exception {

		when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(CustomerController.BASE_URL + "/222").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
