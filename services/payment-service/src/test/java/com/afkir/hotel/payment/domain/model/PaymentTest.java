package com.afkir.hotel.payment.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.afkir.hotel.shared.DomainException;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class PaymentTest {

    private Payment payment() {
        return new Payment(UUID.randomUUID(), new BigDecimal("120.00"), "EUR", "CARD");
    }

    @Test
    void newPaymentIsPending() {
        assertEquals(PaymentStatus.PENDING, payment().getStatus());
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
