package com.enesincekara.client;

import com.enesincekara.dto.response.ApiResponse;
import com.enesincekara.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/api/v1/users")
public interface UserServiceClient {

    @GetMapping("/existsUser/{email}")
    boolean existsUser(@PathVariable("email") String email);

    @GetMapping("/getByEmail/{email}")
    UserDto getUser(@PathVariable("email") String email);


}
