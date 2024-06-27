package com.accenture.accounts.dto;

import lombok.Data;

@Data
public class NewAccountDto {
    private Long customerId;
    private String accountType;
    private String branch;
}
