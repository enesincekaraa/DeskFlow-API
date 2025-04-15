package com.enesincekara.controller;


import com.enesincekara.dto.request.AuthLoginRequest;
import com.enesincekara.dto.request.AuthRegisterRequest;
import com.enesincekara.dto.response.ApiResponse;
import com.enesincekara.dto.response.AuthResponse;
import com.enesincekara.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping("/register")
    public ApiResponse<ResponseEntity<AuthResponse>> register(@RequestBody AuthRegisterRequest request) {

        return new ApiResponse<>(
                true,
                "User registered successfully",
                ResponseEntity.ok(authServiceImpl.register(request))
        );

    }

    @PostMapping("/login")
    public ApiResponse<ResponseEntity<AuthResponse>> login(@Valid @RequestBody AuthLoginRequest request) {
        return new ApiResponse<>(
                true,
                "User logged in successfully",
                ResponseEntity.ok(authServiceImpl.login(request))
        );
    }


    @GetMapping("/secure")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("You have access to a secure endpoint!");
    }

}
