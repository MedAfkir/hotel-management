package com.afkir.hotel.hotel.api.dto;

import java.util.UUID;

import com.afkir.hotel.hotel.domain.model.Room;
import lombok.Builder;

@Builder
public record RoomResponse(UUID id, UUID hotelId, UUID roomTypeId, int floor, String number,
                           String name, boolean available) {

    public static RoomResponse from(Room room) {
        return new RoomResponse(room.getId(), room.getHotelId(), room.getRoomTypeId(), room.getFloor(),
                room.getNumber(), room.getName(), room.isAvailable());
    }

}
