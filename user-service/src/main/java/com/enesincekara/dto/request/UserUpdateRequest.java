package com.enesincekara.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank(message = "First name must not be blank")
        String firstName,
        @NotBlank(message = "Last name must not be blank")
        String lastName,
        @NotBlank(message = "Email must not be blank")
        @Email(message = "Email should be valid")
        String email
) {
}
