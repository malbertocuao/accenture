package com.accenture.accounts.services.implementation;

import com.accenture.accounts.dto.NewTransactionsDto;
import com.accenture.accounts.dto.TransactionsDto;
import com.accenture.accounts.entity.Account;
import com.accenture.accounts.entity.Transactions;
import com.accenture.accounts.exception.ResourceNotFound;
import com.accenture.accounts.mapper.TransactionsMapper;
import com.accenture.accounts.repository.AccountRepository;
import com.accenture.accounts.repository.TransactionsRepository;
import com.accenture.accounts.services.ITransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionsService implements ITransactionsService {

    private AccountRepository accountRepository;
    private TransactionsRepository repository;

    @Override
    public void create(NewTransactionsDto transactionsDto) {

        Account account = accountRepository.findById(transactionsDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFound("Cuenta", "Numero", transactionsDto.getAccountNumber().toString())
        );

        Transactions transactions = TransactionsMapper.mapNewToTransactions(transactionsDto, new Transactions());

        transactions.setBalance(account.getBalance() + transactionsDto.getAmount());

        account.setBalance(transactions.getBalance());

        accountRepository.save(account);
        repository.save(transactions);
    }

    @Override
    public List<TransactionsDto> fetchAccountTransactions(Long accountNumber) {
        List<TransactionsDto> transactionsDtos = new ArrayList<>();

        List<Transactions> transactionsList =  repository.findAllByAccountNumberOrderByTransactionId(accountNumber);

        for (Transactions transactions: transactionsList) {
            transactionsDtos.add(TransactionsMapper.mapToTransactionsDto(transactions, new TransactionsDto()));
        }

        return transactionsDtos;
    }
}
