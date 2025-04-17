package com.enesincekara.dto.response;

public record AccountResponse(
        String firstName,
        String lastName,
        String iban,
        String accountType,
        String email
) {
}
