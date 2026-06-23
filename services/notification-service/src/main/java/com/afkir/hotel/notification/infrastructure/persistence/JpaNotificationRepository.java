package com.afkir.hotel.notification.infrastructure.persistence;

import com.afkir.hotel.notification.domain.model.Notification;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository extends JpaRepository<Notification, UUID> {
}
