package com.afkir.hotel.housekeeping.api.dto;

import com.afkir.hotel.housekeeping.domain.model.TaskType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record CreateTaskRequest(
        @NotNull UUID hotelId,
        @NotNull UUID roomId,
        @NotNull TaskType type,
        @NotNull LocalDate scheduledDate) {
}
