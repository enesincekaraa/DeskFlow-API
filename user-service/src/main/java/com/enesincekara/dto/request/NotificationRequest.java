package com.enesincekara.dto.request;

public record NotificationRequest(
        String to,
        String subject,
        String message
) {}
