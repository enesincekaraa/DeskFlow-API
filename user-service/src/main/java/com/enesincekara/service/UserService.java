package com.enesincekara.service;

import com.enesincekara.dto.login.UserLoginRequest;
import com.enesincekara.dto.request.AuthRequest;
import com.enesincekara.dto.request.UserCreateRequest;
import com.enesincekara.dto.request.UserLoginResponse;
import com.enesincekara.dto.response.UserDto;
import com.enesincekara.dto.request.UserUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {


    UserDto createUser(UserCreateRequest request);

    UserDto getUserById(UUID id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UUID id, UserUpdateRequest request);

    String deleteUser(UUID id);

    UserLoginResponse login(UserLoginRequest request);

    boolean checkLogin(AuthRequest request);

}
