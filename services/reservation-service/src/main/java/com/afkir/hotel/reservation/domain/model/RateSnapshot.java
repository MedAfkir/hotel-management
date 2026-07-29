package com.afkir.hotel.reservation.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "rate_snapshot")
public class RateSnapshot {

    @EmbeddedId
    private RateSnapshotId id;

    private BigDecimal amount;

    private String currency;

    protected RateSnapshot() {
    }

    public RateSnapshot(UUID hotelId, UUID roomTypeId, LocalDate date, BigDecimal amount,
                        String currency) {
        this.id = new RateSnapshotId(hotelId, roomTypeId, date);
        this.amount = amount;
        this.currency = currency;
    }

    public void update(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

}
