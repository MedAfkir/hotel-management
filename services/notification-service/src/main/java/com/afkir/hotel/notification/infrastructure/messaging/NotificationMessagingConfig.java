package com.afkir.hotel.notification.infrastructure.messaging;

import java.util.UUID;
import java.util.function.Consumer;

import com.afkir.hotel.messaging.ReservationCancelled;
import com.afkir.hotel.messaging.ReservationConfirmed;
import com.afkir.hotel.notification.application.NotificationApplicationService;
import com.afkir.hotel.notification.domain.model.NotificationChannel;
import com.afkir.hotel.outbox.InboxGuard;
import com.afkir.hotel.outbox.MessageDeserializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class NotificationMessagingConfig {

    @Bean
    public Consumer<Message<byte[]>> reservationEvents(InboxGuard inboxGuard,
                                                       MessageDeserializer deserializer,
                                                       NotificationApplicationService notifications) {
        return message -> {
            UUID messageId = UUID.fromString((String) message.getHeaders().get("messageId"));
            String type = (String) message.getHeaders().get("type");
            inboxGuard.runOnce(messageId, () -> {
                if (ReservationConfirmed.class.getName().equals(type)) {
                    ReservationConfirmed event =
                            deserializer.deserialize(message.getPayload(), ReservationConfirmed.class);
                    notifications.send(event.guestId().toString(), NotificationChannel.EMAIL,
                            "Reservation confirmed",
                            "Your reservation " + event.reservationId() + " is confirmed.");
                }
                else if (ReservationCancelled.class.getName().equals(type)) {
                    ReservationCancelled event =
                            deserializer.deserialize(message.getPayload(), ReservationCancelled.class);
                    notifications.send(event.guestId().toString(), NotificationChannel.EMAIL,
                            "Reservation cancelled",
                            "Your reservation " + event.reservationId() + " was cancelled: "
                                    + event.reason());
                }
            });
        };
    }

}
