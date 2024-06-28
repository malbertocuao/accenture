package com.accenture.accounts.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewTransactionsDto {
    private Long accountNumber;
    private Float amount;
}
