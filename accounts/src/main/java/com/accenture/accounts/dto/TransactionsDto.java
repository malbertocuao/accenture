package com.accenture.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionsDto {

    private Long accountNumber;
    private LocalDateTime createdDate;
    private Float amount;
    private Float balance;

}
