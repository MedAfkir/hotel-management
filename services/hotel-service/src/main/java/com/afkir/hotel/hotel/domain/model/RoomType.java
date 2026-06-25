package com.afkir.hotel.hotel.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "room_type")
@Builder
@Getter
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

}
