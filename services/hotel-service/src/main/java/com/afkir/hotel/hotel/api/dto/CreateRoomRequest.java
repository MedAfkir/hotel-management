package com.afkir.hotel.hotel.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateRoomRequest(
        @NotNull UUID roomTypeId,
        @Min(0) int floor,
        @NotBlank String number,
        @NotBlank String name) {
}
