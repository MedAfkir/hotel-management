package com.afkir.hotel.notification.api.rest;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import com.afkir.hotel.notification.api.dto.NotificationResponse;
import com.afkir.hotel.notification.api.dto.SendNotificationRequest;
import com.afkir.hotel.notification.application.NotificationApplicationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationApplicationService service;

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
