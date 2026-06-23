package com.afkir.hotel.hotel.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "room")
public class Room {

    @Id
    private UUID id;

    private UUID hotelId;

    private UUID roomTypeId;

    private int floor;

    private String number;

    private String name;

    private boolean available;

    protected Room() {
    }

    public Room(UUID hotelId, UUID roomTypeId, int floor, String number, String name) {
        this.id = UUID.randomUUID();
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.floor = floor;
        this.number = number;
        this.name = name;
        this.available = true;
    }

    public UUID getId() {
        return id;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public UUID getRoomTypeId() {
        return roomTypeId;
    }

    public int getFloor() {
        return floor;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }
}
