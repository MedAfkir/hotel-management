package com.afkir.hotel.notification.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.notification.domain.model.Notification;
import com.afkir.hotel.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepository {

    private final JpaNotificationRepository jpa;

    @Override
    public Notification save(Notification notification) {
        return jpa.save(notification);
    }

    @Override
    public Optional<Notification> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Notification> findAll() {
        return jpa.findAll();
    }

}
