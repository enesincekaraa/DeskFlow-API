package com.enesincekara.notification_service.service;

import com.enesincekara.notification_service.dto.request.NotificationRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {


    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(NotificationRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.to());
        mailMessage.setSubject(request.subject());
        mailMessage.setText(request.message());
        mailSender.send(mailMessage);

    }
}
