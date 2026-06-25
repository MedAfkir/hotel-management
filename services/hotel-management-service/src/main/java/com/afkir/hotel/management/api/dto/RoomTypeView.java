package com.afkir.hotel.management.api.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record RoomTypeView(UUID id, UUID hotelId, String name, int maxOccupancy) {
}
