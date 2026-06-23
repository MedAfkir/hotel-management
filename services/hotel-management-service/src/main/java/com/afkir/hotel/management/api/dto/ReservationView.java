package com.afkir.hotel.management.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ReservationView(
        UUID id,
        UUID hotelId,
        UUID roomTypeId,
        UUID guestId,
        LocalDate startDate,
        LocalDate endDate,
        int numberOfRooms,
        BigDecimal totalAmount,
        String currency,
        String status) {
}
