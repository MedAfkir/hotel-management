package com.afkir.hotel.hotel.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    private UUID id;

    private UUID hotelId;

    private String name;

    private int maxOccupancy;

    protected RoomType() {
    }

    public RoomType(UUID hotelId, String name, int maxOccupancy) {
        this.id = UUID.randomUUID();
        this.hotelId = hotelId;
        this.name = name;
        this.maxOccupancy = maxOccupancy;
    }

    public UUID getId() {
        return id;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }
}
