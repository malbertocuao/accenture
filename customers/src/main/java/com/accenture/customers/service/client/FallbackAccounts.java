package com.accenture.customers.service.client;

import com.accenture.customers.dto.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FallbackAccounts implements AccountsFeignClient {

    @Override
    public ResponseEntity<List<AccountDto>> fetchCustomerAccounts(Long customerId) {

        List<AccountDto> accountDtos = new ArrayList<>();

        AccountDto accountDto = new AccountDto();
        accountDto.setCustomerId(customerId);
        accountDto.setAccountNumber(0L);
        accountDto.setAccountType("El servicio de cuentas no está disponible");

        accountDtos.add(accountDto);

        return ResponseEntity.ok(accountDtos);
    }
}
