package com.enesincekara.dto.request;

public record UserLoginResponse(
        String firstName,
        String lastName,
        String message
) {
}
