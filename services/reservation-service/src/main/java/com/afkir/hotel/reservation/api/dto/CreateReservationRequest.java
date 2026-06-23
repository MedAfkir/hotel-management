package com.afkir.hotel.reservation.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationRequest(
        @NotBlank String idempotencyKey,
        @NotNull UUID hotelId,
        @NotNull UUID roomTypeId,
        @NotNull UUID guestId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @Min(1) int numberOfRooms) {
}
