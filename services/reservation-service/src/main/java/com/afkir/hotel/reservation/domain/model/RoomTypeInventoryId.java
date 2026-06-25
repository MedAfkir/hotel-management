package com.afkir.hotel.reservation.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;

import lombok.Getter;

@Getter
@Embeddable
public class RoomTypeInventoryId implements Serializable {

    private UUID hotelId;

    private UUID roomTypeId;

    private LocalDate date;

    protected RoomTypeInventoryId() {
    }

    public RoomTypeInventoryId(UUID hotelId, UUID roomTypeId, LocalDate date) {
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomTypeInventoryId other)) {
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
