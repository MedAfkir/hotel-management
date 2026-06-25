package com.afkir.hotel.payment.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.payment.domain.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByReservationId(UUID reservationId);

}
