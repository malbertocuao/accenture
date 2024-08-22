package com.accenture.customers.service.implementation;

import com.accenture.customers.dto.AccountDto;
import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.dto.CustomerWithAccounts;
import com.accenture.customers.entity.Customer;
import com.accenture.customers.exception.ResourceAlreadyExists;
import com.accenture.customers.exception.ResourceNotFound;
import com.accenture.customers.mapper.CustomerMapper;
import com.accenture.customers.repository.CustomerRepository;
import com.accenture.customers.service.ICustomerService;
import com.accenture.customers.service.client.AccountsFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;
    private AccountsFeignClient accountsFeignClient;

    @Override
    public void createCustomer(CustomerDto customerDto) {

        Optional<Customer> optionalCustomer = customerRepository.findByDocument(customerDto.getDocument());

        if (optionalCustomer.isPresent()) {
            throw new ResourceAlreadyExists("Cliente", "Documento", customerDto.getDocument());
        }

        Customer customer = CustomerMapper.mapDtoToCustomer(customerDto, new Customer());

        customerRepository.save(customer);
    }

    @Override
    public CustomerDto fetchCustomerByDocument(String document) {

        Customer customer = customerRepository.findByDocument(document).orElseThrow(
                () -> new ResourceNotFound("Cliente", "Documento", document)
        );

        return CustomerMapper.mapCustomerToDto(customer, new CustomerDto());
    }

    @Override
    public CustomerDto fetchCustomerByEmail(String email) {

        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFound("Cliente", "Correo electrónico", email)
        );

        return CustomerMapper.mapCustomerToDto(customer, new CustomerDto());

    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {

        Customer customer = customerRepository.findByDocument(customerDto.getDocument()).orElseThrow(
                () -> new ResourceNotFound("Cliente", "Documento", customerDto.getDocument())
        );

        CustomerMapper.mapDtoToCustomer(customerDto, customer);
        customerRepository.save(customer);

        return customerDto;
    }

    @Override
    public void deleteByDocument(String document) {
        customerRepository.deleteByDocument(document);
    }


    @Override
    public CustomerWithAccounts fetchCustomerWithAccountsByDocument(String document) {
        Customer customer = customerRepository.findByDocument(document).orElseThrow(
                () -> new ResourceNotFound("Cliente", "Documento", document)
        );

        CustomerWithAccounts customerWithAccounts = CustomerMapper.mapCustomerToDtoWithAccounts(customer, new CustomerWithAccounts());

        ResponseEntity<List<AccountDto>> accountsResponse = accountsFeignClient.fetchCustomerAccounts(customer.getCustomerId());

        if (accountsResponse != null) {
            List<AccountDto> accounts = accountsResponse.getBody();
            customerWithAccounts.setAccounts(accounts);
        }

        return customerWithAccounts;
    }

}
