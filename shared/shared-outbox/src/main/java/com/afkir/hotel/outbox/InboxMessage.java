package com.afkir.hotel.outbox;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "inbox_message")
public class InboxMessage {

    @Id
    private UUID id;

    private Instant processedAt;

    protected InboxMessage() {
    }

    public InboxMessage(UUID id, Instant processedAt) {
        this.id = id;
        this.processedAt = processedAt;
    }

}
