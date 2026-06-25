package com.afkir.hotel.rate.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;

import lombok.Getter;

@Getter
@Embeddable
public class RoomTypeRateId implements Serializable {

    private UUID hotelId;

    private UUID roomTypeId;

    private LocalDate date;

    protected RoomTypeRateId() {
    }

    public RoomTypeRateId(UUID hotelId, UUID roomTypeId, LocalDate date) {
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomTypeRateId other)) {
            return false;
        }
        return Objects.equals(hotelId, other.hotelId)
                && Objects.equals(roomTypeId, other.roomTypeId)
                && Objects.equals(date, other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, roomTypeId, date);
    }

}
