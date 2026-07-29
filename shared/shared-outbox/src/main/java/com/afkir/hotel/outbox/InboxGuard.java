package com.afkir.hotel.outbox;

import java.time.Instant;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InboxGuard {

    private final InboxRepository repository;

    @Transactional
    public void runOnce(UUID messageId, Runnable action) {
        if (repository.existsById(messageId)) {
            return;
        }
        action.run();
        repository.save(new InboxMessage(messageId, Instant.now()));
    }

}
