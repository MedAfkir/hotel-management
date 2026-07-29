package com.afkir.hotel.messaging;

import java.util.UUID;

public record PaymentFailed(UUID messageId, UUID sagaId, UUID reservationId, String reason) {
}
