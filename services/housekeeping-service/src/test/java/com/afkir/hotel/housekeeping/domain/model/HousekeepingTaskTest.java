package com.afkir.hotel.housekeeping.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import com.afkir.hotel.shared.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HousekeepingTaskTest {

    @Test
    void newTaskIsPending() {
        assertEquals(TaskStatus.PENDING, task().getStatus());
    }

    private HousekeepingTask task() {
        return new HousekeepingTask(UUID.randomUUID(), UUID.randomUUID(), TaskType.CLEANING,
                LocalDate.of(2026, 7, 1));
    }

    @Test
    void startThenComplete() {
        HousekeepingTask task = task();
        task.start();
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        task.complete();
        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    void cannotCompleteBeforeStart() {
        assertThrows(DomainException.class, () -> task().complete());
    }

}
