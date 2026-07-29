package com.afkir.hotel.messaging;

import java.math.BigDecimal;
import java.util.UUID;

public record ProcessPaymentCommand(UUID messageId, UUID sagaId, UUID reservationId,
                                    BigDecimal amount, String currency, String method) {
}
