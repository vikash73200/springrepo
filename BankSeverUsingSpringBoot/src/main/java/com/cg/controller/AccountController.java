package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Transaction;
import com.cg.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        accountService.deposit(accountId, amount);
        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountId, amount);
        return ResponseEntity.ok("Withdrawal successful");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam BigDecimal amount) {
        accountService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok("Transfer successful");
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getLastTenTransactions(@PathVariable Long accountId) {
        List<Transaction> transactions = accountService.getLastTenTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }
}
