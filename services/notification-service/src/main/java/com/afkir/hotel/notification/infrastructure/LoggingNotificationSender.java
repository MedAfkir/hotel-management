package com.afkir.hotel.notification.infrastructure;

import com.afkir.hotel.notification.application.NotificationSender;
import com.afkir.hotel.notification.domain.model.NotificationChannel;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingNotificationSender implements NotificationSender {

    @Override
    public void send(String recipient, NotificationChannel channel, String subject, String body) {
        log.info("sending {} notification to {} with subject {}", channel, recipient, subject);
    }

}
