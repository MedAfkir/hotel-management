package com.afkir.hotel.payment.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.afkir.hotel.messaging.Bindings;
import com.afkir.hotel.messaging.PaymentCompleted;
import com.afkir.hotel.messaging.PaymentFailed;
import com.afkir.hotel.messaging.ProcessPaymentCommand;
import com.afkir.hotel.outbox.OutboxAppender;
import com.afkir.hotel.payment.domain.model.Payment;
import com.afkir.hotel.payment.domain.model.PaymentStatus;
import com.afkir.hotel.payment.domain.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PaymentApplicationServiceTest {

    private final PaymentRepository repository = mock(PaymentRepository.class);

    private final OutboxAppender outboxAppender = mock(OutboxAppender.class);

    private final PaymentApplicationService service =
            new PaymentApplicationService(repository, outboxAppender);

    @Test
    void handle_whenAuthorized_completesAndEmitsPaymentCompleted() {
        UUID reservationId = UUID.randomUUID();
        when(repository.findByReservationId(reservationId)).thenReturn(List.of());
        when(repository.save(any(Payment.class))).thenAnswer(call -> call.getArgument(0));

        service.handle(command(reservationId, "CARD"));

        ArgumentCaptor<Payment> saved = ArgumentCaptor.forClass(Payment.class);
        verify(repository).save(saved.capture());
        assertThat(saved.getValue().getStatus()).isEqualTo(PaymentStatus.COMPLETED);

        ArgumentCaptor<Object> payload = ArgumentCaptor.forClass(Object.class);
        verify(outboxAppender).append(eq(Bindings.PAYMENT_REPLIES), any(UUID.class), any(UUID.class),
                payload.capture());
        assertThat(payload.getValue()).isInstanceOf(PaymentCompleted.class);
    }

    @Test
    void handle_whenDeclined_failsAndEmitsPaymentFailed() {
        UUID reservationId = UUID.randomUUID();
        when(repository.findByReservationId(reservationId)).thenReturn(List.of());
        when(repository.save(any(Payment.class))).thenAnswer(call -> call.getArgument(0));

        service.handle(command(reservationId, "DECLINE"));

        ArgumentCaptor<Payment> saved = ArgumentCaptor.forClass(Payment.class);
        verify(repository).save(saved.capture());
        assertThat(saved.getValue().getStatus()).isEqualTo(PaymentStatus.FAILED);

        ArgumentCaptor<Object> payload = ArgumentCaptor.forClass(Object.class);
        verify(outboxAppender).append(eq(Bindings.PAYMENT_REPLIES), any(UUID.class), any(UUID.class),
                payload.capture());
        assertThat(payload.getValue()).isInstanceOf(PaymentFailed.class);
    }

    @Test
    void handle_whenPaymentAlreadyExists_doesNotCreateNewPayment() {
        UUID reservationId = UUID.randomUUID();
        Payment existing = new Payment(reservationId, new BigDecimal("100.00"), "EUR", "CARD");
        existing.complete();
        when(repository.findByReservationId(reservationId)).thenReturn(List.of(existing));

        service.handle(command(reservationId, "CARD"));

        verify(repository, never()).save(any(Payment.class));
        ArgumentCaptor<Object> payload = ArgumentCaptor.forClass(Object.class);
        verify(outboxAppender).append(eq(Bindings.PAYMENT_REPLIES), any(UUID.class), any(UUID.class),
                payload.capture());
        assertThat(payload.getValue()).isInstanceOf(PaymentCompleted.class);
    }

    private ProcessPaymentCommand command(UUID reservationId, String method) {
        return new ProcessPaymentCommand(UUID.randomUUID(), reservationId, reservationId,
                new BigDecimal("100.00"), "EUR", method);
    }

}
