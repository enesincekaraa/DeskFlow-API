package com.enesincekara.dto.response;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}
