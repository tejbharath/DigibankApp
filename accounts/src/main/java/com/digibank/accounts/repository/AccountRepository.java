package com.digibank.accounts.repository;

import com.digibank.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCustomerId(long customerId);

    Optional<Account> findByAccountNumber(long accountNumber);
}
