package com.afkir.hotel.rate.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpsertRateRequest(
        @NotNull UUID hotelId,
        @NotNull UUID roomTypeId,
        @NotNull LocalDate date,
        @NotNull @Positive BigDecimal amount,
        @NotBlank String currency) {
}
