package com.afkir.hotel.outbox;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OutboxAppender {

    private final OutboxRepository repository;

    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.MANDATORY)
    public void append(String destination, UUID messageId, UUID sagaId, Object payload) {
        repository.save(new OutboxMessage(messageId, sagaId, destination,
                payload.getClass().getName(), serialize(payload), Instant.now()));
    }

    private String serialize(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        }
        catch (JsonProcessingException ex) {
            throw new IllegalStateException("cannot serialize outbox payload", ex);
        }
    }

}
