package com.afkir.hotel.notification.infrastructure.persistence;

import java.util.UUID;

import com.afkir.hotel.notification.domain.model.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaNotificationRepository extends JpaRepository<Notification, UUID> {
}
