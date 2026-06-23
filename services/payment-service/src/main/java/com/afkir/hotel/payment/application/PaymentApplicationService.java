package com.afkir.hotel.payment.application;

import com.afkir.hotel.payment.domain.model.Payment;
import com.afkir.hotel.payment.domain.repository.PaymentRepository;
import com.afkir.hotel.payment.infrastructure.client.ReservationClient;
import com.afkir.hotel.shared.DomainException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentApplicationService {

    private static final Logger log = LoggerFactory.getLogger(PaymentApplicationService.class);

    private final PaymentRepository repository;
    private final ReservationClient reservationClient;

    public PaymentApplicationService(PaymentRepository repository,
            ReservationClient reservationClient) {
        this.repository = repository;
        this.reservationClient = reservationClient;
    }

    @Transactional
    public Payment processPayment(UUID reservationId, BigDecimal amount, String currency,
            String method) {
        Payment payment = new Payment(reservationId, amount, currency, method);
        payment.complete();
        Payment saved = repository.save(payment);
        try {
            reservationClient.confirm(reservationId);
        } catch (RuntimeException ex) {
            log.warn("could not confirm reservation {} after payment", reservationId);
        }
        return saved;
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
