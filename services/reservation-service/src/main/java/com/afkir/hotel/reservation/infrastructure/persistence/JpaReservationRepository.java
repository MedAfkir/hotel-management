package com.afkir.hotel.reservation.infrastructure.persistence;

import com.afkir.hotel.reservation.domain.model.Reservation;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReservationRepository extends JpaRepository<Reservation, UUID> {

    Optional<Reservation> findByIdempotencyKey(String idempotencyKey);

    List<Reservation> findByGuestId(UUID guestId);
}
