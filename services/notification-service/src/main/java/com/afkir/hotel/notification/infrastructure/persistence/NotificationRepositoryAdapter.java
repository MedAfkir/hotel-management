package com.afkir.hotel.notification.infrastructure.persistence;

import com.afkir.hotel.notification.domain.model.Notification;
import com.afkir.hotel.notification.domain.repository.NotificationRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class NotificationRepositoryAdapter implements NotificationRepository {

    private final JpaNotificationRepository jpa;

    public NotificationRepositoryAdapter(JpaNotificationRepository jpa) {
        this.jpa = jpa;
    }

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
