package com.afkir.hotel.reservation.domain.event;

import java.util.UUID;

public record ReservationPaid(UUID reservationId) {
}
