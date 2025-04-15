package com.enesincekara.service;

import com.enesincekara.dto.request.AuthLoginRequest;
import com.enesincekara.dto.request.AuthRegisterRequest;
import com.enesincekara.dto.response.AuthResponse;

public interface AuthService {


    AuthResponse register(AuthRegisterRequest request);

    AuthResponse login(AuthLoginRequest request);
}
