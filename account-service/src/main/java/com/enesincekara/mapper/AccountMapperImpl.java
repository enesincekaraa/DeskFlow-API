package com.enesincekara.mapper;

import com.enesincekara.dto.request.AccountCreateRequest;
import com.enesincekara.dto.request.AccountUpdateRequest;
import com.enesincekara.dto.response.AccountResponse;
import com.enesincekara.model.Account;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(
                account.getFirstName(),
                account.getLastName(),
                account.getIban(),
                account.getAccountType(),
                account.getEmail()

        );
    }

    @Override
    public Account toAccount(AccountCreateRequest request) {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setAccountType(request.accountType());
        account.setIban(request.iban());
        account.setEmail(request.email());
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        return account;
    }

    @Override
    public Account fromUpdateRequestToAccount(AccountUpdateRequest request) {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setAccountType(request.accountType());
        account.setIban(request.iban());
        account.setEmail(account.getEmail());
        account.setUpdatedAt(LocalDateTime.now());
        account.setCreatedAt(account.getCreatedAt());
        return account;
    }
}
