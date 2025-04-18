package com.enesincekara.service.impl;

import com.enesincekara.client.NotificationClient;
import com.enesincekara.client.UserServiceClient;
import com.enesincekara.dto.request.AccountCreateRequest;
import com.enesincekara.dto.request.AccountUpdateRequest;
import com.enesincekara.dto.request.NotificationRequest;
import com.enesincekara.dto.response.AccountResponse;
import com.enesincekara.dto.response.UserDto;
import com.enesincekara.exception.AccountNotFoundException;
import com.enesincekara.exception.AccountTypeAlreadyException;
import com.enesincekara.exception.UserNotFoundException;
import com.enesincekara.mapper.AccountMapper;
import com.enesincekara.model.Account;
import com.enesincekara.repository.AccountRepository;
import com.enesincekara.service.AccountService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserServiceClient userServiceClient;
    private final NotificationClient notificationClient;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, UserServiceClient userServiceClient, NotificationClient notificationClient) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.userServiceClient = userServiceClient;
        this.notificationClient = notificationClient;
    }

    public AccountResponse createAccount(AccountCreateRequest request){

        Account account = accountMapper.toAccount(request);

        boolean existsUser = userServiceClient.existsUser(account.getEmail());
        if (!existsUser){
            throw new UserNotFoundException("User not found" + account.getEmail());
        }
        UserDto userDto = userServiceClient.getUser(account.getEmail());
        boolean existsType = accountRepository.existsByEmailAndAccountType(request.email(), request.accountType());
        if (!existsType)
        {
                account.setId(null);
                account.setFirstName(userDto.firstName());
                account.setLastName(userDto.lastName());
                account.setEmail(userDto.email());
                account.setAccountType(request.accountType());
                account.setIban(request.iban());
                account.setCreatedAt(LocalDateTime.now());
                account.setUpdatedAt(LocalDateTime.now());
                accountRepository.save(account);
                notificationClient.sendNotification(new NotificationRequest(
                        userDto.email(),
                        "Account Created",
                        "Your account has been created successfully."
                ));
        }
        else
        {
            throw new AccountTypeAlreadyException("Account type already exists for this user");
        }
        return accountMapper.toAccountResponse(account);
    }


    @Override
    public AccountResponse updateAccount(String email, AccountUpdateRequest request){

        Account updateAccount = accountMapper.fromUpdateRequestToAccount(request);
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with email: " + email));

        account.setId(account.getId());
        account.setFirstName(account.getFirstName());
        account.setLastName(account.getLastName());
        account.setEmail(account.getEmail());
        account.setIban(request.iban());
        account.setAccountType(request.accountType());
        account.setUpdatedAt(LocalDateTime.now());
        account.setCreatedAt(account.getCreatedAt());
        accountRepository.save(account);

        notificationClient.sendNotification(new NotificationRequest(
                account.getEmail(),
                "Account Updated",
                "Your account has been updated successfully."
        ));


        return accountMapper.toAccountResponse(account);

    }




}
