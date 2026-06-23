package com.afkir.hotel.guest.infrastructure.persistence;

import com.afkir.hotel.guest.domain.model.Guest;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGuestRepository extends JpaRepository<Guest, UUID> {

    Optional<Guest> findByEmail(String email);
}
