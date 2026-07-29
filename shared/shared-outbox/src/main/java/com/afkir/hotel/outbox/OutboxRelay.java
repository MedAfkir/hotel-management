package com.afkir.hotel.outbox;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;

@Component
@RequiredArgsConstructor
public class OutboxRelay {

    private final OutboxRepository repository;

    private final StreamBridge streamBridge;

    @Scheduled(fixedDelayString = "${outbox.relay.fixed-delay:1000}")
    @Transactional
    public void publishPending() {
        List<OutboxMessage> pending = repository.findTop100BySentAtIsNullOrderByCreatedAtAsc();
        for (OutboxMessage message : pending) {
            if (publish(message)) {
                message.markSent(Instant.now());
            }
            else {
                message.incrementAttempts();
            }
        }
    }

    private boolean publish(OutboxMessage message) {
        MessageBuilder<byte[]> builder = MessageBuilder
                .withPayload(message.getPayload().getBytes(StandardCharsets.UTF_8))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                .setHeader("messageId", message.getId().toString())
                .setHeader("type", message.getType());
        if (message.getSagaId() != null) {
            builder.setHeader("sagaId", message.getSagaId().toString());
        }
        return streamBridge.send(message.getDestination(), builder.build());
    }

}
