package com.afkir.hotel.payment.infrastructure.messaging;

import java.util.UUID;
import java.util.function.Consumer;

import com.afkir.hotel.messaging.ProcessPaymentCommand;
import com.afkir.hotel.outbox.InboxGuard;
import com.afkir.hotel.outbox.MessageDeserializer;
import com.afkir.hotel.payment.application.PaymentApplicationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class PaymentMessagingConfig {

    @Bean
    public Consumer<Message<byte[]>> paymentCommands(InboxGuard inboxGuard,
                                                     MessageDeserializer deserializer,
                                                     PaymentApplicationService service) {
        return message -> {
            UUID messageId = UUID.fromString((String) message.getHeaders().get("messageId"));
            ProcessPaymentCommand command =
                    deserializer.deserialize(message.getPayload(), ProcessPaymentCommand.class);
            inboxGuard.runOnce(messageId, () -> service.handle(command));
        };
    }

}
