package com.afkir.hotel.reservation.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.afkir.hotel.reservation.domain.model.Reservation;
import lombok.Builder;

@Builder
public record ReservationResponse(
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

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(reservation.getId(), reservation.getHotelId(),
                reservation.getRoomTypeId(), reservation.getGuestId(), reservation.getStartDate(),
                reservation.getEndDate(), reservation.getNumberOfRooms(), reservation.getTotalAmount(),
                reservation.getCurrency(), reservation.getStatus().name());
    }

}
