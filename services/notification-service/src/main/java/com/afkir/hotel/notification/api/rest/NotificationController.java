package com.afkir.hotel.notification.api.rest;

import com.afkir.hotel.notification.api.dto.NotificationResponse;
import com.afkir.hotel.notification.api.dto.SendNotificationRequest;
import com.afkir.hotel.notification.application.NotificationApplicationService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationApplicationService service;

    public NotificationController(NotificationApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> send(
            @Valid @RequestBody SendNotificationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(NotificationResponse.from(
                service.send(request.recipient(), request.channel(), request.subject(),
                        request.body())));
    }

    @GetMapping("/{id}")
    public NotificationResponse get(@PathVariable UUID id) {
        return NotificationResponse.from(service.getNotification(id));
    }

    @GetMapping
    public List<NotificationResponse> list() {
        return service.list().stream().map(NotificationResponse::from).toList();
    }
}
