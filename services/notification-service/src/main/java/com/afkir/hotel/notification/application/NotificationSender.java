package com.afkir.hotel.notification.application;

import com.afkir.hotel.notification.domain.model.NotificationChannel;

public interface NotificationSender {

    void send(String recipient, NotificationChannel channel, String subject, String body);

}
