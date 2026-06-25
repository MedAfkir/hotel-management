package com.afkir.hotel.housekeeping.api.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.afkir.hotel.housekeeping.domain.model.HousekeepingTask;
import lombok.Builder;

@Builder
public record TaskResponse(UUID id, UUID hotelId, UUID roomId, String type, String status,
                           String assignee, LocalDate scheduledDate) {

    public static TaskResponse from(HousekeepingTask task) {
        return new TaskResponse(task.getId(), task.getHotelId(), task.getRoomId(),
                task.getType().name(), task.getStatus().name(), task.getAssignee(),
                task.getScheduledDate());
    }

}
