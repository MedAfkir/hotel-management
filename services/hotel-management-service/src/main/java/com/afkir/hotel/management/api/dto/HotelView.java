package com.afkir.hotel.management.api.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record HotelView(UUID id, String name, String address, String city) {
}
