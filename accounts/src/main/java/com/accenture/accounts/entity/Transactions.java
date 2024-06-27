package com.accenture.accounts.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "balance")
    private Float balance;

}
