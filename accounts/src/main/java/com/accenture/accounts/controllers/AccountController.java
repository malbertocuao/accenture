package com.accenture.accounts.controllers;

import com.accenture.accounts.dto.*;
import com.accenture.accounts.services.IAccountService;
import com.accenture.accounts.services.ITransactionsService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class AccountController {

    @NonNull
    private IAccountService accountService;

    @NonNull
    private ITransactionsService transactionsService;

    @NonNull
    private Environment environment;

    @NonNull
    private SupportInfoDto supportInfo;

    @GetMapping(value = "/support-info")
    public ResponseEntity<SupportInfoDto> supportInfo() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supportInfo);
    }


    @GetMapping(value = "/java-home")
    public ResponseEntity<String> javaVersion() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

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

    @PostMapping(value = "/createTransaction")
    public ResponseEntity<ResponseDto> createTransaction(@RequestBody NewTransactionsDto transactionsDto) {

        transactionsService.create(transactionsDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Transaccion creada correctamente !!"));
    }

    @GetMapping(value = "/fetchTransactions/{accountNumber}")
    public ResponseEntity<List<TransactionsDto>> fetchTransactions(@PathVariable Long accountNumber) {

        List<TransactionsDto> transactionsDtos = transactionsService.fetchAccountTransactions(accountNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionsDtos);
    }

}
