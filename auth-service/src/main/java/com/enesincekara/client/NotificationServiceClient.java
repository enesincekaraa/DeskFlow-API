package com.enesincekara.client;

import com.enesincekara.dto.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service", path = "/api/v1/notification")
public interface NotificationServiceClient {

    @PostMapping("/send")
    void sendNotification(NotificationRequest notificationRequest);

}
