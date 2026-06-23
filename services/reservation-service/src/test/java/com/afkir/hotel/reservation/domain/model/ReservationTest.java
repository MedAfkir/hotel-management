package com.afkir.hotel.reservation.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.afkir.hotel.shared.DomainException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ReservationTest {

    private Reservation reservation() {
        return new Reservation(UUID.randomUUID(), "key-1", UUID.randomUUID(), UUID.randomUUID(),
                UUID.randomUUID(), LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 3), 1,
                new BigDecimal("200.00"), "EUR");
    }

    @Test
    void newReservationIsPending() {
        assertEquals(ReservationStatus.PENDING, reservation().getStatus());
    }

    @Test
    void markPaidFromPending() {
        Reservation reservation = reservation();
        reservation.markPaid();
        assertEquals(ReservationStatus.PAID, reservation.getStatus());
    }

    @Test
    void cannotRefundPendingReservation() {
        assertThrows(DomainException.class, () -> reservation().refund());
    }

    @Test
    void cannotMarkPaidTwice() {
        Reservation reservation = reservation();
        reservation.markPaid();
        assertThrows(DomainException.class, reservation::markPaid);
    }
}
