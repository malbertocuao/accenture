package com.accenture.accounts.services.implementation;

import com.accenture.accounts.dto.AccountDto;
import com.accenture.accounts.dto.NewAccountDto;
import com.accenture.accounts.entity.Account;
import com.accenture.accounts.exception.ResourceNotFound;
import com.accenture.accounts.mapper.AccountMapper;
import com.accenture.accounts.repository.AccountRepository;
import com.accenture.accounts.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private AccountRepository repository;

    @Override
    public AccountDto create(NewAccountDto accountDto) {

        Account account = AccountMapper.mapToNewAccount(accountDto, new Account());

        Long accountNumber = 100000000L + new Random().nextInt(900000000);
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0F);

        Account savedAccount = repository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount, new AccountDto());
    }

    @Override
    public AccountDto fetch(Long accountNumber) {

        Account account = repository.findById(accountNumber).orElseThrow(
                () -> new ResourceNotFound("Cuenta", "Numero de cuenta", accountNumber.toString())
        );

        return AccountMapper.mapToAccountDto(account, new AccountDto());

    }

    @Override
    public List<AccountDto> fetchCustomerAccounts(Long customerId) {

        List<AccountDto> accountDtos = new ArrayList<>();

        List<Account> accountList = repository.findAllByCustomerIdOrderByAccountNumber(customerId);

        for (Account account: accountList) {
            accountDtos.add(AccountMapper.mapToAccountDto(account, new AccountDto()));
        }

        return accountDtos;
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        return null;
    }
}
