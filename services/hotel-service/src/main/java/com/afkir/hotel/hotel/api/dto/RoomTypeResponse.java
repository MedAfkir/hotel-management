package com.afkir.hotel.hotel.api.dto;

import java.util.UUID;

import com.afkir.hotel.hotel.domain.model.RoomType;
import lombok.Builder;

@Builder
public record RoomTypeResponse(UUID id, UUID hotelId, String name, int maxOccupancy) {

    public static RoomTypeResponse from(RoomType roomType) {
        return new RoomTypeResponse(roomType.getId(), roomType.getHotelId(), roomType.getName(),
                roomType.getMaxOccupancy());
    }
}
