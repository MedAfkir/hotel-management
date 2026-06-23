package com.afkir.hotel.notification.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notification")
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

    public UUID getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public Instant getSentAt() {
        return sentAt;
    }
}
