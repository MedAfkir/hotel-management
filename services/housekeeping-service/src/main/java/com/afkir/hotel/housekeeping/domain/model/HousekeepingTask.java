package com.afkir.hotel.housekeeping.domain.model;

import com.afkir.hotel.shared.DomainException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "housekeeping_task")
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

    public void complete() {
        requireStatus(TaskStatus.IN_PROGRESS);
        status = TaskStatus.DONE;
    }

    private void requireStatus(TaskStatus expected) {
        if (status != expected) {
            throw new DomainException("expected status " + expected + " but was " + status);
        }
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
