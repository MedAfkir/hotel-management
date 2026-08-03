package com.afkir.hotel.reservation.api.dto;

import jakarta.validation.constraints.NotBlank;

public record PayReservationRequest(@NotBlank String method) {
}
