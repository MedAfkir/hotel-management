package com.afkir.hotel.payment.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.afkir.hotel.shared.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentTest {

    @Test
    void newPaymentIsPending() {
        assertEquals(PaymentStatus.PENDING, payment().getStatus());
    }

    private Payment payment() {
        return new Payment(UUID.randomUUID(), new BigDecimal("120.00"), "EUR", "CARD");
    }

    @Test
    void completeThenRefund() {
        Payment payment = payment();
        payment.complete();
        assertEquals(PaymentStatus.COMPLETED, payment.getStatus());
        payment.refund();
        assertEquals(PaymentStatus.REFUNDED, payment.getStatus());
    }

    @Test
    void cannotRefundPendingPayment() {
        assertThrows(DomainException.class, () -> payment().refund());
    }

}
