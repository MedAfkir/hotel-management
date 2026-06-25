package com.afkir.hotel.housekeeping.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

import com.afkir.hotel.shared.DomainException;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "housekeeping_task")
@Builder
@Getter
public class HousekeepingTask {

    @Id
    private UUID id;

    private UUID hotelId;

    private UUID roomId;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String assignee;

    private LocalDate scheduledDate;

    protected HousekeepingTask() {
    }

    public HousekeepingTask(UUID hotelId, UUID roomId, TaskType type, LocalDate scheduledDate) {
        this.id = UUID.randomUUID();
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.type = type;
        this.scheduledDate = scheduledDate;
        this.status = TaskStatus.PENDING;
    }

    public void assignTo(String assignee) {
        this.assignee = assignee;
    }

    public void start() {
        requireStatus(TaskStatus.PENDING);
        status = TaskStatus.IN_PROGRESS;
    }

    private void requireStatus(TaskStatus expected) {
        if (status != expected) {
            throw new DomainException("expected status " + expected + " but was " + status);
        }
    }

    public void complete() {
        requireStatus(TaskStatus.IN_PROGRESS);
        status = TaskStatus.DONE;
    }

    public UUID getId() {
        return id;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public TaskType getType() {
        return type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getAssignee() {
        return assignee;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

}
