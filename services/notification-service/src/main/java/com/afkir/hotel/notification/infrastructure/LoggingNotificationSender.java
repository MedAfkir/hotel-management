package com.afkir.hotel.notification.infrastructure;

import com.afkir.hotel.notification.application.NotificationSender;
import com.afkir.hotel.notification.domain.model.NotificationChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingNotificationSender implements NotificationSender {

    private static final Logger log = LoggerFactory.getLogger(LoggingNotificationSender.class);

    @Override
    public void send(String recipient, NotificationChannel channel, String subject, String body) {
        log.info("sending {} notification to {} with subject {}", channel, recipient, subject);
    }
}
