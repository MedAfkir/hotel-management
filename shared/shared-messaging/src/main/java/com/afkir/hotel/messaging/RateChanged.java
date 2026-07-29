package com.afkir.hotel.messaging;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RateChanged(UUID messageId, UUID hotelId, UUID roomTypeId, LocalDate date,
                          BigDecimal amount, String currency) {
}
