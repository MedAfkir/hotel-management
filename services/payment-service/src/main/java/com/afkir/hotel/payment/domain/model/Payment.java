package com.afkir.hotel.payment.domain.model;

import com.afkir.hotel.shared.DomainException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private UUID id;

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

    public void fail() {
        requireStatus(PaymentStatus.PENDING);
        status = PaymentStatus.FAILED;
    }

    public void refund() {
        requireStatus(PaymentStatus.COMPLETED);
        status = PaymentStatus.REFUNDED;
    }

    private void requireStatus(PaymentStatus expected) {
        if (status != expected) {
            throw new DomainException("expected status " + expected + " but was " + status);
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMethod() {
        return method;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
