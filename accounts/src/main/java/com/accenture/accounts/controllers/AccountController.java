package com.accenture.accounts.controllers;

import com.accenture.accounts.dto.AccountDto;
import com.accenture.accounts.dto.NewAccountDto;
import com.accenture.accounts.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class AccountController {

    private IAccountService accountService;

    @PostMapping(value = "/createAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody NewAccountDto accountDto) {
        AccountDto savedAccount = accountService.create(accountDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedAccount);
    }

    @GetMapping(value = "/fetchAccount/{accountNumber}")
    public ResponseEntity<AccountDto> fetchAccount(@PathVariable Long accountNumber) {

        AccountDto accountDto = accountService.fetch(accountNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountDto);
    }

    @GetMapping(value = "/fetchCustomerAccounts/{customerId}")
    public ResponseEntity<List<AccountDto>> fetchCustomerAccounts(@PathVariable Long customerId) {

        List<AccountDto> accounts = accountService.fetchCustomerAccounts(customerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accounts);
    }

}
