package com.afkir.hotel.reservation.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoomTypeInventoryTest {

    @Test
    void reservesWithinCapacity() {
        RoomTypeInventory inventory = inventory(10);
        inventory.reserve(4, 1.0);
        assertEquals(4, inventory.getTotalReserved());
    }

    private RoomTypeInventory inventory(int total) {
        return new RoomTypeInventory(UUID.randomUUID(), UUID.randomUUID(), LocalDate.of(2026, 7, 1),
                total);
    }

    @Test
    void allowsOverbookingUpToFactor() {
        RoomTypeInventory inventory = inventory(10);
        inventory.reserve(11, 1.10);
        assertEquals(11, inventory.getTotalReserved());
    }

    @Test
    void rejectsBeyondOverbookingFactor() {
        RoomTypeInventory inventory = inventory(10);
        assertThrows(InsufficientInventoryException.class, () -> inventory.reserve(12, 1.10));
    }

    @Test
    void releaseReducesReserved() {
        RoomTypeInventory inventory = inventory(10);
        inventory.reserve(5, 1.0);
        inventory.release(2);
        assertEquals(3, inventory.getTotalReserved());
    }

}
