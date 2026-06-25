package com.afkir.hotel.hotel.api.dto;

import java.util.UUID;

import com.afkir.hotel.hotel.domain.model.Hotel;
import lombok.Builder;

@Builder
public record HotelResponse(UUID id, String name, String address, String city) {

    public static HotelResponse from(Hotel hotel) {
        return new HotelResponse(hotel.getId(), hotel.getName(), hotel.getAddress(), hotel.getCity());
    }

}
