package com.accenture.accounts.mapper;

import com.accenture.accounts.dto.NewTransactionsDto;
import com.accenture.accounts.dto.TransactionsDto;
import com.accenture.accounts.entity.Transactions;

public class TransactionsMapper {

    public static TransactionsDto mapToTransactionsDto(Transactions transactions, TransactionsDto transactionsDto) {
        transactionsDto.setAmount(transactions.getAmount());
        transactionsDto.setBalance(transactions.getBalance());
        transactionsDto.setAccountNumber(transactions.getAccountNumber());
        transactionsDto.setCreatedDate(transactions.getCreatedDate());

        return transactionsDto;
    }

    public static Transactions mapToTransactions(TransactionsDto transactionsDto, Transactions transactions) {
        transactions.setAccountNumber(transactionsDto.getAccountNumber());
        transactions.setAmount(transactionsDto.getAmount());
        transactions.setBalance(transactionsDto.getBalance());
        transactions.setCreatedDate(transactionsDto.getCreatedDate());

        return transactions;
    }

    public static Transactions mapNewToTransactions(NewTransactionsDto transactionsDto, Transactions transactions) {
        transactions.setAccountNumber(transactionsDto.getAccountNumber());
        transactions.setAmount(transactionsDto.getAmount());

        return transactions;
    }

}
