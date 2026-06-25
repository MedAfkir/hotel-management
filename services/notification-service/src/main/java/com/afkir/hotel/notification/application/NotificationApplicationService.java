package com.afkir.hotel.notification.application;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.afkir.hotel.notification.domain.model.Notification;
import com.afkir.hotel.notification.domain.model.NotificationChannel;
import com.afkir.hotel.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NotificationApplicationService {

    private final NotificationRepository repository;

    private final NotificationSender sender;

    @Transactional
    public Notification send(String recipient, NotificationChannel channel, String subject,
                             String body) {
        Notification notification = new Notification(recipient, channel, subject, body);
        try {
            sender.send(recipient, channel, subject, body);
            notification.markSent(Instant.now());
        }
        catch (RuntimeException ex) {
            notification.markFailed();
        }
        return repository.save(notification);
    }

    @Transactional(readOnly = true)
    public Notification getNotification(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "notification not found"));
    }

    @Transactional(readOnly = true)
    public List<Notification> list() {
        return repository.findAll();
    }

}
