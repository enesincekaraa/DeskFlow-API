package com.enesincekara.client;

import com.enesincekara.dto.request.AuthLoginRequest;
import com.enesincekara.dto.request.UserCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", path = "/api/v1/users")
public interface UserServiceClient {

    @PostMapping("/create")
    void createUser(@RequestBody UserCreateRequest request);

    @PostMapping("/auth/check")
    Boolean checkLogin(@RequestBody AuthLoginRequest request);


}
