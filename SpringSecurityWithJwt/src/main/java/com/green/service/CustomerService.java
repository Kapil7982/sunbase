package com.green.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.green.exception.CustomerException;
import com.green.model.Customer;
import com.green.model.CustomerDTO;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer addCustomer(CustomerDTO customerDTO);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
    public Customer updateCustomer(Integer id, Customer customerDetails)throws CustomerException;
	
	public Page<Customer> getAllCustomers(Pageable pageable)throws CustomerException;
	
	public Customer getCustomerById(Integer id)throws CustomerException;
	
	public void deleteCustomer(Integer id)throws CustomerException;
	
	Page<Customer> searchCustomers(String keyword, Pageable pageable) throws CustomerException;

}
