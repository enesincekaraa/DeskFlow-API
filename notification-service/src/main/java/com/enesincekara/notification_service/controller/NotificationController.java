package com.enesincekara.notification_service.controller;

import com.enesincekara.notification_service.dto.request.NotificationRequest;
import com.enesincekara.notification_service.dto.response.ApiResponse;
import com.enesincekara.notification_service.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final EmailService emailService;


    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ApiResponse<ResponseEntity<String>> sendMail(@RequestBody NotificationRequest request) {
        emailService.sendEmail(request);
        return new ApiResponse<>(
                true,
                "Email sent successfully",
                ResponseEntity.ok("Email sent successfully")
        );
    }

}
