package com.afkir.hotel.payment.infrastructure.persistence;

import com.afkir.hotel.payment.domain.model.Payment;
import com.afkir.hotel.payment.domain.repository.PaymentRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository jpa;

    public PaymentRepositoryAdapter(JpaPaymentRepository jpa) {
        this.jpa = jpa;
    }

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
