package com.afkir.hotel.payment.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.afkir.hotel.payment.domain.model.Payment;
import com.afkir.hotel.payment.domain.repository.PaymentRepository;
import com.afkir.hotel.payment.infrastructure.client.ReservationClient;
import com.afkir.hotel.shared.DomainException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService {

    private final PaymentRepository repository;

    private final ReservationClient reservationClient;

    @Transactional
    public Payment processPayment(UUID reservationId, BigDecimal amount, String currency,
                                  String method) {
        Payment payment = new Payment(reservationId, amount, currency, method);
        payment.complete();
        Payment saved = repository.save(payment);
        reservationClient.confirm(reservationId);
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
