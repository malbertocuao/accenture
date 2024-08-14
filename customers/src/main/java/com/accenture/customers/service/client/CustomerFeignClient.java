package com.accenture.customers.service.client;

import com.accenture.customers.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("accounts")
public interface CustomerFeignClient {

    @GetMapping(value = "/api/fetchCustomerAccounts/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> fetchCustomerAccounts(@PathVariable Long customerId);

}
