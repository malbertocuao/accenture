package com.accenture.accounts.repository;

import com.accenture.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByCustomerIdOrderByAccountNumber(Long customerId);

}
