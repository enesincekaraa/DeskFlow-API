package com.enesincekara.notification_service.dto.request;

public record NotificationRequest(
        String to,
        String subject,
        String message
) {}