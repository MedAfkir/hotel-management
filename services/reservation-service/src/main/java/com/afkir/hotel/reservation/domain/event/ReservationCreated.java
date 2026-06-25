package com.afkir.hotel.reservation.domain.event;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;

@Builder
public record ReservationCreated(UUID reservationId, UUID guestId, LocalDate startDate, LocalDate endDate) {
}
