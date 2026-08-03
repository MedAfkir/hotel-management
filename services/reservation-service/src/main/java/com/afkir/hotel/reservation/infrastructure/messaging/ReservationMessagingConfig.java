package com.afkir.hotel.reservation.infrastructure.messaging;

import java.util.UUID;
import java.util.function.Consumer;

import com.afkir.hotel.messaging.PaymentCompleted;
import com.afkir.hotel.messaging.PaymentFailed;
import com.afkir.hotel.messaging.RateChanged;
import com.afkir.hotel.outbox.InboxGuard;
import com.afkir.hotel.outbox.MessageDeserializer;
import com.afkir.hotel.reservation.application.RateReadModel;
import com.afkir.hotel.reservation.application.saga.PaymentSagaOrchestrator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class ReservationMessagingConfig {

    @Bean
    public Consumer<Message<byte[]>> paymentReplies(InboxGuard inboxGuard,
                                                    MessageDeserializer deserializer,
                                                    PaymentSagaOrchestrator orchestrator) {
        return message -> {
            UUID messageId = UUID.fromString((String) message.getHeaders().get("messageId"));
            String type = (String) message.getHeaders().get("type");
            inboxGuard.runOnce(messageId, () -> {
                if (PaymentCompleted.class.getName().equals(type)) {
                    orchestrator.onPaymentCompleted(
                            deserializer.deserialize(message.getPayload(), PaymentCompleted.class));
                }
                else if (PaymentFailed.class.getName().equals(type)) {
                    orchestrator.onPaymentFailed(
                            deserializer.deserialize(message.getPayload(), PaymentFailed.class));
                }
            });
        };
    }

    @Bean
    public Consumer<Message<byte[]>> rateEvents(InboxGuard inboxGuard,
                                                MessageDeserializer deserializer,
                                                RateReadModel rateReadModel) {
        return message -> {
            UUID messageId = UUID.fromString((String) message.getHeaders().get("messageId"));
            inboxGuard.runOnce(messageId, () ->
                    rateReadModel.apply(deserializer.deserialize(message.getPayload(), RateChanged.class)));
        };
    }

}
