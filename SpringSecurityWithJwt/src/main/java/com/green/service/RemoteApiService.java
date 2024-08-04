package com.green.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.green.model.Customer;
import com.green.model.CustomerDTO;
import com.green.repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RemoteApiService {

	
    private static final String API_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    private static final String AUTH_TOKEN = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";

    private static final Logger logger = LoggerFactory.getLogger(RemoteApiService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void fetchAndSaveCustomers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + AUTH_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CustomerDTO[]> response = restTemplate.exchange(
                    API_URL + "?cmd=get_customer_list",
                    HttpMethod.GET,
                    entity,
                    CustomerDTO[].class
            );

            List<CustomerDTO> customerDTOs = Arrays.asList(response.getBody());

            for (CustomerDTO dto : customerDTOs) {
                try {
                    saveOrUpdateCustomer(dto);
                } catch (Exception e) {
                    logger.error("Error processing customer: " + dto.getEmail(), e);
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching customers from API", e);
        }
    }

    private void saveOrUpdateCustomer(CustomerDTO dto) {
        try {
            Optional<Customer> existingCustomerOpt = customerRepository.findByEmail(dto.getEmail());

            Customer customerToSave;
            if (existingCustomerOpt.isPresent()) {
                customerToSave = existingCustomerOpt.get();
                logger.info("Updating existing customer: " + dto.getEmail());
            } else {
                customerToSave = new Customer();
                customerToSave.setEmail(dto.getEmail());
                logger.info("Creating new customer: " + dto.getEmail());
            }

            updateCustomerFromDTO(customerToSave, dto);

            customerRepository.save(customerToSave);
            logger.info("Successfully saved/updated customer: " + dto.getEmail());
        } catch (DataAccessException e) {
            logger.error("Database error while saving/updating customer: " + dto.getEmail(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while saving/updating customer: " + dto.getEmail(), e);
            throw e;
        }
    }

    private void updateCustomerFromDTO(Customer customer, CustomerDTO dto) {
    	customer.setUuid(dto.getUuid());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setStreet(dto.getStreet());
        customer.setAddress(dto.getAddress());
        customer.setCity(dto.getCity());
        customer.setState(dto.getState());
        customer.setPhone(dto.getPhone());
    }
    
    public void deleteCustomerFromRemoteApi(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + AUTH_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String deleteUrl = API_URL + "?cmd=delete&uuid=" + uuid;

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                deleteUrl,
                HttpMethod.POST,
                entity,
                String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("Customer deleted successfully from remote API: " + uuid);
            } else {
                logger.error("Failed to delete customer from remote API: " + uuid + ". Status: " + response.getStatusCode() + ", Body: " + response.getBody());
                throw new RuntimeException("Failed to delete customer from remote API. Status: " + response.getStatusCode() + ", Body: " + response.getBody());
            }
        } catch (HttpClientErrorException e) {
            logger.error("HTTP Client Error deleting customer from remote API: " + uuid + ". Status: " + e.getStatusCode() + ", Response: " + e.getResponseBodyAsString(), e);
            throw new RuntimeException("Error deleting customer from remote API: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error deleting customer from remote API: " + uuid, e);
            throw new RuntimeException("Unexpected error deleting customer from remote API: " + e.getMessage(), e);
        }
    }
}