package com.enesincekara.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AccountUpdateRequest(
        @NotBlank(message = "IBAN is required")
        String iban,
        @NotBlank(message = "Account type is required")
        String accountType,

        LocalDateTime updatedAt
) {
}
