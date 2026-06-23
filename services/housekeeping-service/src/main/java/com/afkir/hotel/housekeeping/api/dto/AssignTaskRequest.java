package com.afkir.hotel.housekeeping.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AssignTaskRequest(@NotBlank String assignee) {
}
