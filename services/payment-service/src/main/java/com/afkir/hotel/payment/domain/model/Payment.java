package com.afkir.hotel.payment.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;

import com.afkir.hotel.shared.DomainException;
import lombok.Getter;

@Entity
@Table(name = "payment")
@Getter
public class Payment {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID reservationId;

    private BigDecimal amount;

    private String currency;

    private String method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    protected Payment() {
    }

    public Payment(UUID reservationId, BigDecimal amount, String currency, String method) {
        this.id = UUID.randomUUID();
        this.reservationId = reservationId;
        this.amount = amount;
        this.currency = currency;
        this.method = method;
        this.status = PaymentStatus.PENDING;
    }

    public void complete() {
        requireStatus(PaymentStatus.PENDING);
        status = PaymentStatus.COMPLETED;
    }

    private void requireStatus(PaymentStatus expected) {
        if (status != expected) {
            throw new DomainException("expected status " + expected + " but was " + status);
        }
    }

    public void fail() {
        requireStatus(PaymentStatus.PENDING);
        status = PaymentStatus.FAILED;
    }

    public void refund() {
        requireStatus(PaymentStatus.COMPLETED);
        status = PaymentStatus.REFUNDED;
    }

}
