package com.afkir.hotel.payment.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.afkir.hotel.payment.domain.model.Payment;
import lombok.Builder;

@Builder
public record PaymentResponse(UUID id, UUID reservationId, BigDecimal amount, String currency,
                              String method, String status) {

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getReservationId(), payment.getAmount(),
                payment.getCurrency(), payment.getMethod(), payment.getStatus().name());
    }

}
