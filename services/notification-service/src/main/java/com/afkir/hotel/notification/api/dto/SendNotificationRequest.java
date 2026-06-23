package com.afkir.hotel.notification.api.dto;

import com.afkir.hotel.notification.domain.model.NotificationChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SendNotificationRequest(
        @NotBlank String recipient,
        @NotNull NotificationChannel channel,
        @NotBlank String subject,
        @NotBlank String body) {
}
