package com.afkir.hotel.management.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateHotelRequest(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String city) {
}
