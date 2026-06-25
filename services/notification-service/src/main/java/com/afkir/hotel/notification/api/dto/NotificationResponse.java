package com.afkir.hotel.notification.api.dto;

import java.time.Instant;
import java.util.UUID;

import com.afkir.hotel.notification.domain.model.Notification;
import lombok.Builder;

@Builder
public record NotificationResponse(UUID id, String recipient, String channel, String subject,
                                   String body, String status, Instant sentAt) {

    public static NotificationResponse from(Notification notification) {
        return new NotificationResponse(notification.getId(), notification.getRecipient(),
                notification.getChannel().name(), notification.getSubject(), notification.getBody(),
                notification.getStatus().name(), notification.getSentAt());
    }

}
