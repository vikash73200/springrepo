package com.cg.loader;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cg.entity.Account;
import com.cg.repository.AccountRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insert initial data
        Account account1 = new Account();
        account1.setAccountNumber("123456");
        account1.setAccountHolderName("John Doe");
        account1.setBalance(BigDecimal.valueOf(1000));
        accountRepository.save(account1);

        Account account2 = new Account();
        account2.setAccountNumber("789012");
        account2.setAccountHolderName("Jane Smith");
        account2.setBalance(BigDecimal.valueOf(2000));
        accountRepository.save(account2);
    }
}
