package com.afkir.hotel.reservation.infrastructure.client;

import java.math.BigDecimal;

public record RateResponse(BigDecimal amount, String currency) {
}
