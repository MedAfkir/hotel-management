package com.afkir.hotel.reservation.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record SetInventoryRequest(
        @NotNull UUID hotelId,
        @NotNull UUID roomTypeId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @Min(0) int totalInventory) {
}
