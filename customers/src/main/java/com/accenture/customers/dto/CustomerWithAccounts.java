package com.accenture.customers.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerWithAccounts {

    private String document;
    private String name;
    private String email;
    private String phone;
    private String address;

    private List<AccountDto> accounts;
}
