package com.afkir.hotel.payment.api.dto;

import com.afkir.hotel.payment.domain.model.Payment;
import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResponse(UUID id, UUID reservationId, BigDecimal amount, String currency,
        String method, String status) {

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getReservationId(), payment.getAmount(),
                payment.getCurrency(), payment.getMethod(), payment.getStatus().name());
    }
}
