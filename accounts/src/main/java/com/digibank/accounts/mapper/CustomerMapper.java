package com.digibank.accounts.mapper;

import com.digibank.accounts.dto.CustomerDTO;
import com.digibank.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO CustomerDTO, Customer customer) {
        customer.setName(CustomerDTO.getName());
        customer.setEmail(CustomerDTO.getEmail());
        customer.setMobileNumber(CustomerDTO.getMobileNumber());
        return customer;
    }
}
