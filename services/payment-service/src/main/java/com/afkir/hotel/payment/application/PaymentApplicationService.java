package com.afkir.hotel.payment.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.messaging.Bindings;
import com.afkir.hotel.messaging.PaymentCompleted;
import com.afkir.hotel.messaging.PaymentFailed;
import com.afkir.hotel.messaging.ProcessPaymentCommand;
import com.afkir.hotel.outbox.OutboxAppender;
import com.afkir.hotel.payment.domain.model.Payment;
import com.afkir.hotel.payment.domain.model.PaymentStatus;
import com.afkir.hotel.payment.domain.repository.PaymentRepository;
import com.afkir.hotel.shared.DomainException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService {

    private final PaymentRepository repository;

    private final OutboxAppender outboxAppender;

    @Transactional
    public void handle(ProcessPaymentCommand command) {
        Optional<Payment> existing = repository.findByReservationId(command.reservationId())
                .stream().findFirst();
        if (existing.isPresent()) {
            reply(command, existing.get());
            return;
        }
        Payment payment = new Payment(command.reservationId(), command.amount(), command.currency(),
                command.method());
        if (authorized(command)) {
            payment.complete();
        }
        else {
            payment.fail();
        }
        reply(command, repository.save(payment));
    }

    private void reply(ProcessPaymentCommand command, Payment payment) {
        UUID messageId = UUID.randomUUID();
        if (payment.getStatus() == PaymentStatus.COMPLETED) {
            outboxAppender.append(Bindings.PAYMENT_REPLIES, messageId, command.sagaId(),
                    new PaymentCompleted(messageId, command.sagaId(), command.reservationId(),
                            payment.getId()));
        }
        else {
            outboxAppender.append(Bindings.PAYMENT_REPLIES, messageId, command.sagaId(),
                    new PaymentFailed(messageId, command.sagaId(), command.reservationId(),
                            "payment declined"));
        }
    }

    private boolean authorized(ProcessPaymentCommand command) {
        return command.amount() != null && command.amount().signum() > 0
                && !"DECLINE".equalsIgnoreCase(command.method());
    }

    @Transactional
    public Payment refund(UUID paymentId) {
        Payment payment = getPayment(paymentId);
        payment.refund();
        return repository.save(payment);
    }

    @Transactional(readOnly = true)
    public Payment getPayment(UUID paymentId) {
        return repository.findById(paymentId)
                .orElseThrow(() -> new DomainException("payment not found: " + paymentId));
    }

    @Transactional(readOnly = true)
    public List<Payment> findByReservation(UUID reservationId) {
        return repository.findByReservationId(reservationId);
    }

}
