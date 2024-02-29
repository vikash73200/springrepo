package com.cg.service;

import java.math.BigDecimal;
import java.util.List;

import com.cg.entity.Transaction;

public interface AccountService {
    void deposit(Long accountId, BigDecimal amount);
    void withdraw(Long accountId, BigDecimal amount);
    void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
    List<Transaction> getLastTenTransactions(Long accountId);
}