package com.afkir.hotel.reservation.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.reservation.domain.model.Reservation;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(UUID id);

    Optional<Reservation> findByIdempotencyKey(String idempotencyKey);

    List<Reservation> findByGuestId(UUID guestId);

}
