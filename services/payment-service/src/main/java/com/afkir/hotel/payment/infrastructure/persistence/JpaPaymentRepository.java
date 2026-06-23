package com.afkir.hotel.payment.infrastructure.persistence;

import com.afkir.hotel.payment.domain.model.Payment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByReservationId(UUID reservationId);
}
