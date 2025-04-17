package com.enesincekara.service.impl;

import com.enesincekara.dto.login.UserLoginRequest;
import com.enesincekara.dto.request.*;
import com.enesincekara.dto.response.UserDto;
import com.enesincekara.exception.EmailAlreadyException;
import com.enesincekara.exception.PasswordException;
import com.enesincekara.exception.UserNotFoundException;
import com.enesincekara.mapper.UserMapper;
import com.enesincekara.model.User;
import com.enesincekara.repository.UserRepository;
import com.enesincekara.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserCreateRequest request) {
        boolean exists = userRepository.existsByEmail(request.email());
        if (exists) {
            throw new EmailAlreadyException("Email already exists: " + request.email());
        }
        User user = mapper.toUser(request);
        user.setId(null);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);

        return mapper.toUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(UUID id) {
        User user = findById(id);
        return mapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return users.stream()
                .map(mapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser( UUID id, UserUpdateRequest request) {

        User user = findById(id);
        User requestUser = mapper.toUserFromUpdate(request);


        user.setFirstName(requestUser.getFirstName());
        user.setLastName(requestUser.getLastName());
        user.setEmail(requestUser.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return mapper.toUserDto(user);
    }

    @Override
    public String deleteUser(UUID id) {
        User user = findById(id);
        if (user != null) {
            userRepository.deleteById(id);
        }

        return "User deleted successfully";

    }
    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + request.email()));

        if (!user.getPassword().equals(request.password())) {
            throw new PasswordException("Password is incorrect");
        }

        return new UserLoginResponse("Login successful", user.getFirstName(), user.getLastName());
    }

    @Override
    public boolean checkLogin(AuthRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException("Email not found"));

        boolean isValid = passwordEncoder.matches(request.password(), user.getPassword());

        if (!isValid) {
            throw new RuntimeException("Invalid password");
        }

        return true;
    }

    @Override
    public void checkUserExists(String email) {
        userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public boolean existsByEmail(String email) {
        boolean existsUser= userRepository.existsByEmail(email);
        if (!existsUser) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return true;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return mapper.toUserDto(user);
    }


    private User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("User not found with id: " + id));
    }
}
