package com.enesincekara.controller;

import com.enesincekara.dto.login.UserLoginRequest;
import com.enesincekara.dto.request.AuthRequest;
import com.enesincekara.dto.request.UserCreateRequest;
import com.enesincekara.dto.request.UserLoginResponse;
import com.enesincekara.dto.response.ApiResponse;
import com.enesincekara.dto.response.UserDto;
import com.enesincekara.dto.request.UserUpdateRequest;
import com.enesincekara.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ApiResponse<ResponseEntity<UserDto>> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserDto createdUser = userService.createUser(request);
        return new ApiResponse<>(
                true,
                "User created successfully",
                ResponseEntity.ok(createdUser)
        );
    }

    @GetMapping
    public ApiResponse<ResponseEntity<List<UserDto>>> getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ApiResponse<>(
                true,
                "Users found successfully",
                ResponseEntity.ok(users)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ResponseEntity<UserDto>> getUserById(@PathVariable UUID id) {
        UserDto user = userService.getUserById(id);
        return new ApiResponse<>(
                true,
                "User found successfully",
                ResponseEntity.ok(user)
        );
    }


    @PutMapping("/{id}")
    public ApiResponse<ResponseEntity<UserDto>> updateUser(@PathVariable UUID id,@Valid @RequestBody UserUpdateRequest request) {
        UserDto updatedUser = userService.updateUser(id, request);
        return new ApiResponse<>(
                true,
                "User updated successfully",
                ResponseEntity.ok(updatedUser)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ResponseEntity<String>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ApiResponse<>(
                true,
                "User deleted successfully",
                ResponseEntity.ok("User deleted successfully")
        );
    }

    @PostMapping("/login")
    public ApiResponse<ResponseEntity<UserLoginResponse>> login(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse loginResponse = userService.login(request);
        return new ApiResponse<>(
                true,
                "User logged in successfully",
                ResponseEntity.ok(loginResponse)
        );
    }

    @PostMapping("/auth/check")
    public ResponseEntity<Boolean> checkLogin(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.checkLogin(request));
    }

}
