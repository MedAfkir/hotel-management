package com.afkir.hotel.messaging;

import java.util.UUID;

public record ReservationCancelled(UUID messageId, UUID reservationId, UUID guestId, String reason) {
}
