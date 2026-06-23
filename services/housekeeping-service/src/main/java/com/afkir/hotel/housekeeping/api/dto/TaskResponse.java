package com.afkir.hotel.housekeeping.api.dto;

import com.afkir.hotel.housekeeping.domain.model.HousekeepingTask;
import java.time.LocalDate;
import java.util.UUID;

public record TaskResponse(UUID id, UUID hotelId, UUID roomId, String type, String status,
        String assignee, LocalDate scheduledDate) {

    public static TaskResponse from(HousekeepingTask task) {
        return new TaskResponse(task.getId(), task.getHotelId(), task.getRoomId(),
                task.getType().name(), task.getStatus().name(), task.getAssignee(),
                task.getScheduledDate());
    }
}
