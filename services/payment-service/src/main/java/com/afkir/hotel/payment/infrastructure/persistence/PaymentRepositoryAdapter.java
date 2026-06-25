package com.afkir.hotel.payment.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.payment.domain.model.Payment;
import com.afkir.hotel.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository jpa;

    @Override
    public Payment save(Payment payment) {
        return jpa.save(payment);
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Payment> findByReservationId(UUID reservationId) {
        return jpa.findByReservationId(reservationId);
    }

}
