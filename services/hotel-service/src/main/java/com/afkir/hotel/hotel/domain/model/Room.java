package com.afkir.hotel.hotel.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "room")
@Getter
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

}
