package com.accenture.accounts.repository;

import com.accenture.accounts.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findAllByAccountNumberOrderByTransactionId(Long accountNumber);

}
