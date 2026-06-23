package com.afkir.hotel.rate.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "room_type_rate")
public class RoomTypeRate {

    @EmbeddedId
    private RoomTypeRateId id;

    private BigDecimal amount;

    private String currency;

    protected RoomTypeRate() {
    }

    public RoomTypeRate(UUID hotelId, UUID roomTypeId, LocalDate date, BigDecimal amount,
            String currency) {
        this.id = new RoomTypeRateId(hotelId, roomTypeId, date);
        this.amount = amount;
        this.currency = currency;
    }

    public void changeRate(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public RoomTypeRateId getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
