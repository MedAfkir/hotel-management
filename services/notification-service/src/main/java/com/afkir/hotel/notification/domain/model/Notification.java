package com.afkir.hotel.notification.domain.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "notification")
@Getter
@Builder
public class Notification {

    @Id
    private UUID id;

    private String recipient;

    @Enumerated(EnumType.STRING)
    private NotificationChannel channel;

    private String subject;

    @Column(length = 2000)
    private String body;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private Instant sentAt;

    protected Notification() {
    }

    public Notification(String recipient, NotificationChannel channel, String subject, String body) {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.channel = channel;
        this.subject = subject;
        this.body = body;
        this.status = NotificationStatus.PENDING;
    }

    public void markSent(Instant sentAt) {
        this.status = NotificationStatus.SENT;
        this.sentAt = sentAt;
    }

    public void markFailed() {
        this.status = NotificationStatus.FAILED;
    }

}
