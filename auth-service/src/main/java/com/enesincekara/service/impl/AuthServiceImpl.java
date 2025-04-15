package com.enesincekara.service.impl;

import com.enesincekara.client.NotificationServiceClient;
import com.enesincekara.client.UserServiceClient;
import com.enesincekara.dto.request.AuthLoginRequest;
import com.enesincekara.dto.request.AuthRegisterRequest;
import com.enesincekara.dto.request.NotificationRequest;
import com.enesincekara.dto.request.UserCreateRequest;
import com.enesincekara.dto.response.AuthResponse;
import com.enesincekara.exception.InvalidEmailOrPasswordException;
import com.enesincekara.security.JwtTokenProvider;
import com.enesincekara.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserServiceClient userServiceClient;
    private final NotificationServiceClient notificationServiceClient;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserServiceClient userServiceClient, NotificationServiceClient notificationServiceClient, JwtTokenProvider jwtTokenProvider) {
        this.userServiceClient = userServiceClient;
        this.notificationServiceClient = notificationServiceClient;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public AuthResponse register(AuthRegisterRequest request){
        userServiceClient.createUser(new UserCreateRequest(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password()
        ));
        notificationServiceClient.sendNotification(
                new NotificationRequest(
                        request.email(),
                        "Welcome to our service",
                        "Hello " + request.firstName() + ",\n\n" +
                                "Thank you for registering with us. We are excited to have you on board!\n\n" +
                                "Best regards,\n" +
                                "The Team"
                ));
        String token = jwtTokenProvider.generateToken(request.email());
        return new AuthResponse(
            token,"Registration successful"
        );
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {

        boolean isValid = userServiceClient.checkLogin(request);

        if (!isValid) {
            throw  new InvalidEmailOrPasswordException("Invalid email or password");
        }

        notificationServiceClient.sendNotification(new NotificationRequest(
                request.email(),
                "Login Notification",
                "Hello,\n\n" +
                        "You have successfully logged in to your account.\n\n" +
                        "Best regards,\n" +
                        "The Team"
        ));
        String token = jwtTokenProvider.generateToken(request.email());
        return new AuthResponse(
                token, "Login successful"
        );
    }
}
