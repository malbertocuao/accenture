package com.accenture.customers.service;

import com.accenture.customers.dto.CustomerDto;

public interface ICustomerService {

    void createCustomer(CustomerDto customerDto);

    CustomerDto fetchCustomerByDocument(String document);

    CustomerDto fetchCustomerByEmail(String email);

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteByDocument(String document);
}
