package com.enesincekara.mapper;

import com.enesincekara.dto.request.UserCreateRequest;
import com.enesincekara.dto.response.UserDto;
import com.enesincekara.dto.request.UserUpdateRequest;
import com.enesincekara.model.User;

public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(UserCreateRequest request);

    User toUserFromUserDto(UserDto userDto);


    User toUserFromUpdate(UserUpdateRequest request);
}
