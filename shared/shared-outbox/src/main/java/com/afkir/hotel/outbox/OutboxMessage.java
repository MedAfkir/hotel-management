package com.afkir.hotel.outbox;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "outbox_message")
public class OutboxMessage {

    @Id
    private UUID id;

    private UUID sagaId;

    private String destination;

    private String type;

    @Column(length = 4000)
    private String payload;

    private Instant createdAt;

    private Instant sentAt;

    private int attempts;

    protected OutboxMessage() {
    }

    public OutboxMessage(UUID id, UUID sagaId, String destination, String type, String payload,
                         Instant createdAt) {
        this.id = id;
        this.sagaId = sagaId;
        this.destination = destination;
        this.type = type;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public void markSent(Instant sentAt) {
        this.sentAt = sentAt;
    }

    public void incrementAttempts() {
        this.attempts++;
    }

}
