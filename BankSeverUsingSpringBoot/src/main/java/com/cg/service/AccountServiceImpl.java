package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.entity.Account;
import com.cg.entity.Transaction;
import com.cg.entity.TransactionType;
import com.cg.repository.AccountRepository;
import com.cg.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);

        saveTransaction(account, amount, TransactionType.CR, "Deposit");
    }

    @Override
    @Transactional
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(newBalance);
        accountRepository.save(account);

        saveTransaction(account, amount, TransactionType.DR, "Withdrawal");
    }

    @Override
    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        withdraw(fromAccountId, amount);
        deposit(toAccountId, amount);
    }

    @Override
    public List<Transaction> getLastTenTransactions(Long accountId) {
        return transactionRepository.findFirst10ByAccountIdOrderByDateDesc(accountId);
    }

    private void saveTransaction(Account account, BigDecimal amount, TransactionType type, String description) {
        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setTransactionId(generateTransactionId());
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setAccount(account);
        transactionRepository.save(transaction);
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
