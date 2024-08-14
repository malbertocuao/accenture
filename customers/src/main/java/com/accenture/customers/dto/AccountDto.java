package com.accenture.customers.dto;

import lombok.Data;

@Data
public class AccountDto {

    private Long customerId;
    private Long accountNumber;
    private String accountType;
    private String branch;
    private Float balance;

}
