package com.afkir.hotel.outbox;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InboxRepository extends JpaRepository<InboxMessage, UUID> {
}
