package com.accenture.customers.controllers;

import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.dto.CustomerWithAccounts;
import com.accenture.customers.service.ICustomerService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class ClientsController {

    ICustomerService customerService;

    @GetMapping(value = "/fetchWithAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerWithAccounts> fetchWithAccounts(
            @RequestParam
            @NotEmpty(message = "El campo número de doucmento no puede quedar en blanco")
            @Size(min = 5, max = 20, message = "El documento debe contener entre 5 y 20 caracteres de longitud")
            String document) {

        CustomerWithAccounts customerWithAccounts = customerService.fetchCustomerWithAccountsByDocument(document);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(customerWithAccounts);
    }

}
