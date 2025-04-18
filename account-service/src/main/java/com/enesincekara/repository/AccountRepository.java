package com.enesincekara.repository;

import com.enesincekara.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {


    boolean existsByEmailAndAccountType(String email, String accountType);

    Optional<Account> findByEmail(String email);
}
