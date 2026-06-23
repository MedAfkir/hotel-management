package com.afkir.hotel.reservation.application.command;

import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationCommand(
        String idempotencyKey,
        UUID hotelId,
        UUID roomTypeId,
        UUID guestId,
        LocalDate startDate,
        LocalDate endDate,
        int numberOfRooms) {
}
