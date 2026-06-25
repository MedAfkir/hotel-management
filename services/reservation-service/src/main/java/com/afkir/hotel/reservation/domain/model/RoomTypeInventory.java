package com.afkir.hotel.reservation.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import com.afkir.hotel.shared.DomainException;
import lombok.Getter;

@Getter
@Entity
@Table(name = "room_type_inventory")
public class RoomTypeInventory {

    @EmbeddedId
    private RoomTypeInventoryId id;

    private int totalInventory;

    private int totalReserved;

    @Version
    private long version;

    protected RoomTypeInventory() {
    }

    public RoomTypeInventory(UUID hotelId, UUID roomTypeId, LocalDate date, int totalInventory) {
        this.id = new RoomTypeInventoryId(hotelId, roomTypeId, date);
        this.totalInventory = totalInventory;
        this.totalReserved = 0;
    }

    public void reserve(int rooms, double overbookingFactor) {
        if (rooms <= 0) {
            throw new DomainException("rooms to reserve must be positive");
        }
        int capacity = (int) Math.floor(totalInventory * overbookingFactor);
        if (totalReserved + rooms > capacity) {
            throw new InsufficientInventoryException(id.getRoomTypeId(), id.getDate());
        }
        totalReserved += rooms;
    }

    public void release(int rooms) {
        if (rooms <= 0) {
            throw new DomainException("rooms to release must be positive");
        }
        totalReserved = Math.max(0, totalReserved - rooms);
    }

    public void setTotalInventory(int totalInventory) {
        this.totalInventory = totalInventory;
    }

}
