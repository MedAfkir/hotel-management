package com.afkir.hotel.messaging;

import java.util.UUID;

public record ReservationConfirmed(UUID messageId, UUID reservationId, UUID guestId) {
}
