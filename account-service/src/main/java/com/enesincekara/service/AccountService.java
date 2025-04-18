package com.enesincekara.service;

import com.enesincekara.dto.request.AccountCreateRequest;
import com.enesincekara.dto.request.AccountUpdateRequest;
import com.enesincekara.dto.response.AccountResponse;

public interface AccountService {
    AccountResponse createAccount(AccountCreateRequest request);
    AccountResponse updateAccount(String email, AccountUpdateRequest request);
}
