package com.accenture.customers.mapper;

import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapCustomerToDto(Customer customer, CustomerDto customerDto){
        customerDto.setAddress(customer.getAddress());
        customerDto.setDocument(customer.getDocument());
        customerDto.setName(customer.getName());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());

        return customerDto;
    }

    public static Customer mapDtoToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setAddress(customerDto.getAddress());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setDocument(customerDto.getDocument());

        return customer;
    }

}
