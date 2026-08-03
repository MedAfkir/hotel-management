package com.afkir.hotel.messaging;

import java.util.UUID;

public record PaymentCompleted(UUID messageId, UUID sagaId, UUID reservationId, UUID paymentId) {
}
