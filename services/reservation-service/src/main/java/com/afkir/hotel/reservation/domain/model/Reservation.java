package com.afkir.hotel.reservation.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

import com.afkir.hotel.shared.DomainException;
import lombok.Getter;

@Getter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String idempotencyKey;

    private UUID hotelId;

    private UUID roomTypeId;

    private UUID guestId;

    private LocalDate startDate;

    private LocalDate endDate;

    private int numberOfRooms;

    private BigDecimal totalAmount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    protected Reservation() {
    }

    public Reservation(UUID id, String idempotencyKey, UUID hotelId, UUID roomTypeId, UUID guestId,
                       LocalDate startDate, LocalDate endDate, int numberOfRooms, BigDecimal totalAmount,
                       String currency) {
        this.id = id;
        this.idempotencyKey = idempotencyKey;
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.guestId = guestId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfRooms = numberOfRooms;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.status = ReservationStatus.PENDING;
    }

    public void markPaid() {
        requireStatus(ReservationStatus.PENDING);
        status = ReservationStatus.PAID;
    }

    private void requireStatus(ReservationStatus expected) {
        if (status != expected) {
            throw new DomainException("expected status " + expected + " but was " + status);
        }
    }

    public void cancel() {
        if (status != ReservationStatus.PENDING && status != ReservationStatus.PAID) {
            throw new DomainException("only pending or paid reservations can be canceled");
        }
        status = ReservationStatus.CANCELED;
    }

    public void reject() {
        requireStatus(ReservationStatus.PENDING);
        status = ReservationStatus.REJECTED;
    }

    public void refund() {
        requireStatus(ReservationStatus.PAID);
        status = ReservationStatus.REFUNDED;
    }

}
