package com.afkir.hotel.reservation.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.reservation.domain.model.Reservation;
import com.afkir.hotel.reservation.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryAdapter implements ReservationRepository {

    private final JpaReservationRepository jpa;

    @Override
    public Reservation save(Reservation reservation) {
        return jpa.save(reservation);
    }

    @Override
    public Optional<Reservation> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public Optional<Reservation> findByIdempotencyKey(String idempotencyKey) {
        return jpa.findByIdempotencyKey(idempotencyKey);
    }

    @Override
    public List<Reservation> findByGuestId(UUID guestId) {
        return jpa.findByGuestId(guestId);
    }

}
