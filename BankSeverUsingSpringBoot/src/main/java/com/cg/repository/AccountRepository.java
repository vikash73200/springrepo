package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}