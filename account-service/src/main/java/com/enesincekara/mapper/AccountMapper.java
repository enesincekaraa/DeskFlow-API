package com.enesincekara.mapper;

import com.enesincekara.dto.request.AccountCreateRequest;
import com.enesincekara.dto.request.AccountUpdateRequest;
import com.enesincekara.dto.response.AccountResponse;
import com.enesincekara.model.Account;

public interface AccountMapper {

    AccountResponse toAccountResponse(Account account);
    Account toAccount(AccountCreateRequest request);
    Account fromUpdateRequestToAccount(AccountUpdateRequest request);
}
