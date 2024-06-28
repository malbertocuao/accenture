package com.accenture.accounts.services;

import com.accenture.accounts.dto.NewTransactionsDto;
import com.accenture.accounts.dto.TransactionsDto;

import java.util.List;

public interface ITransactionsService {

    void create(NewTransactionsDto transactionsDto);

    List<TransactionsDto> fetchAccountTransactions(Long accountNumber);
}
