package com.accenture.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AccountDto {

    private Long customerId;
    private Long accountNumber;
    private String accountType;
    private String branch;
    private Float balance;

}
