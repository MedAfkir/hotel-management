package com.afkir.hotel.reservation.application.saga;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
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
import com.afkir.hotel.shared.DomainException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PaymentSagaOrchestratorTest {

    private final ReservationApplicationService reservations = mock(ReservationApplicationService.class);

    private final OutboxAppender outboxAppender = mock(OutboxAppender.class);

    private final PaymentSagaOrchestrator orchestrator =
            new PaymentSagaOrchestrator(reservations, outboxAppender);

    @Test
    void startPayment_whenPending_appendsProcessPaymentCommand() {
        UUID id = UUID.randomUUID();
        when(reservations.getReservation(id)).thenReturn(reservation(id));

        orchestrator.startPayment(id, "CARD");

        ArgumentCaptor<Object> payload = ArgumentCaptor.forClass(Object.class);
        verify(outboxAppender).append(eq(Bindings.PAYMENT_COMMANDS), any(UUID.class), eq(id),
                payload.capture());
        assertThat(payload.getValue()).isInstanceOfSatisfying(ProcessPaymentCommand.class, command -> {
            assertThat(command.reservationId()).isEqualTo(id);
            assertThat(command.amount()).isEqualByComparingTo("100.00");
            assertThat(command.method()).isEqualTo("CARD");
        });
    }

    @Test
    void startPayment_whenNotPending_throws() {
        UUID id = UUID.randomUUID();
        Reservation paid = reservation(id);
        paid.markPaid();
        when(reservations.getReservation(id)).thenReturn(paid);

        assertThatThrownBy(() -> orchestrator.startPayment(id, "CARD"))
                .isInstanceOf(DomainException.class);
        verify(outboxAppender, never()).append(any(), any(), any(), any());
    }

    @Test
    void onPaymentCompleted_whenApplied_appendsReservationConfirmed() {
        UUID id = UUID.randomUUID();
        Reservation reservation = reservation(id);
        when(reservations.markPaidIfPending(id)).thenReturn(Optional.of(reservation));

        orchestrator.onPaymentCompleted(new PaymentCompleted(UUID.randomUUID(), id, id, UUID.randomUUID()));

        ArgumentCaptor<Object> payload = ArgumentCaptor.forClass(Object.class);
        verify(outboxAppender).append(eq(Bindings.RESERVATION_EVENTS), any(UUID.class), eq(id),
                payload.capture());
        assertThat(payload.getValue()).isInstanceOf(ReservationConfirmed.class);
    }

    @Test
    void onPaymentCompleted_whenAlreadyHandled_isNoOp() {
        UUID id = UUID.randomUUID();
        when(reservations.markPaidIfPending(id)).thenReturn(Optional.empty());

        orchestrator.onPaymentCompleted(new PaymentCompleted(UUID.randomUUID(), id, id, UUID.randomUUID()));

        verify(outboxAppender, never()).append(any(), any(), any(), any());
    }

    @Test
    void onPaymentFailed_whenCompensated_appendsReservationCancelled() {
        UUID id = UUID.randomUUID();
        Reservation reservation = reservation(id);
        when(reservations.rejectAndReleaseIfPending(id)).thenReturn(Optional.of(reservation));

        orchestrator.onPaymentFailed(new PaymentFailed(UUID.randomUUID(), id, id, "declined"));

        ArgumentCaptor<Object> payload = ArgumentCaptor.forClass(Object.class);
        verify(outboxAppender).append(eq(Bindings.RESERVATION_EVENTS), any(UUID.class), eq(id),
                payload.capture());
        assertThat(payload.getValue()).isInstanceOf(ReservationCancelled.class);
    }

    private Reservation reservation(UUID id) {
        return new Reservation(id, "idem-" + id, UUID.randomUUID(), UUID.randomUUID(),
                UUID.randomUUID(), LocalDate.now(), LocalDate.now().plusDays(1), 1,
                new BigDecimal("100.00"), "EUR");
    }

}
