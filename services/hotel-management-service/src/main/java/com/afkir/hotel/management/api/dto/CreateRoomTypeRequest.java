package com.afkir.hotel.management.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;

@Builder
public record CreateRoomTypeRequest(
        @NotBlank String name,
        @Min(1) int maxOccupancy) {
}
