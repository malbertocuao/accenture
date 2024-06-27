package com.accenture.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class AccountWithTransactionsDto {
    private AccountDto account;
    private List<TransactionsDto> transactions;
}
