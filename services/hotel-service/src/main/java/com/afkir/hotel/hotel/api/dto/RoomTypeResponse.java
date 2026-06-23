package com.afkir.hotel.hotel.api.dto;

import com.afkir.hotel.hotel.domain.model.RoomType;
import java.util.UUID;

public record RoomTypeResponse(UUID id, UUID hotelId, String name, int maxOccupancy) {

    public static RoomTypeResponse from(RoomType roomType) {
        return new RoomTypeResponse(roomType.getId(), roomType.getHotelId(), roomType.getName(),
                roomType.getMaxOccupancy());
    }
}
