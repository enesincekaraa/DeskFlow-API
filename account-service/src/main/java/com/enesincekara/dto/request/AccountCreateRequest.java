package com.enesincekara.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AccountCreateRequest(
        @NotBlank(message = "IBAN is required")
        String iban,

        @NotBlank(message = "Account type is required")
        String accountType,

        @NotBlank(message = "Email is required")
        String email

)
{}
