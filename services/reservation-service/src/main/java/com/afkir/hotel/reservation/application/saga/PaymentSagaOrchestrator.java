package com.afkir.hotel.reservation.application.saga;

import java.util.UUID;

import com.afkir.hotel.messaging.Bindings;
import com.afkir.hotel.messaging.PaymentCompleted;
import com.afkir.hotel.messaging.PaymentFailed;
import com.afkir.hotel.messaging.ProcessPaymentCommand;
import com.afkir.hotel.messaging.ReservationCancelled;
import com.afkir.hotel.messaging.ReservationConfirmed;
import com.afkir.hotel.outbox.OutboxAppender;
import com.afkir.hotel.reservation.application.ReservationApplicationService;
import com.afkir.hotel.reservation.domain.model.Reservation;
import com.afkir.hotel.reservation.domain.model.ReservationStatus;
import com.afkir.hotel.shared.DomainException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentSagaOrchestrator {

    private final ReservationApplicationService reservations;

    private final OutboxAppender outboxAppender;

    @Transactional
    public void startPayment(UUID reservationId, String method) {
        Reservation reservation = reservations.getReservation(reservationId);
        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new DomainException("reservation is not awaiting payment: " + reservationId);
        }
        UUID messageId = UUID.randomUUID();
        outboxAppender.append(Bindings.PAYMENT_COMMANDS, messageId, reservationId,
                new ProcessPaymentCommand(messageId, reservationId, reservationId,
                        reservation.getTotalAmount(), reservation.getCurrency(), method));
    }

    @Transactional
    public void onPaymentCompleted(PaymentCompleted event) {
        reservations.markPaidIfPending(event.reservationId()).ifPresent(reservation -> {
            UUID messageId = UUID.randomUUID();
            outboxAppender.append(Bindings.RESERVATION_EVENTS, messageId, event.sagaId(),
                    new ReservationConfirmed(messageId, reservation.getId(),
                            reservation.getGuestId()));
        });
    }

    @Transactional
    public void onPaymentFailed(PaymentFailed event) {
        reservations.rejectAndReleaseIfPending(event.reservationId()).ifPresent(reservation -> {
            UUID messageId = UUID.randomUUID();
            outboxAppender.append(Bindings.RESERVATION_EVENTS, messageId, event.sagaId(),
                    new ReservationCancelled(messageId, reservation.getId(),
                            reservation.getGuestId(), event.reason()));
        });
    }

}
