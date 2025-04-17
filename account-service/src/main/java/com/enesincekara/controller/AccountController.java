package com.enesincekara.controller;

import com.enesincekara.dto.request.AccountCreateRequest;
import com.enesincekara.dto.response.AccountResponse;
import com.enesincekara.dto.response.ApiResponse;
import com.enesincekara.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ApiResponse<ResponseEntity<AccountResponse>> createAccount(@RequestBody AccountCreateRequest request) {
        AccountResponse accountResponse = accountService.createAccount(request);
        return new ApiResponse<>(
                true,
                "Account created successfully",
                ResponseEntity.ok(accountResponse)
        );
    }
}
