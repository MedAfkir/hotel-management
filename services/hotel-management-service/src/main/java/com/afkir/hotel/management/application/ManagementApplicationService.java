package com.afkir.hotel.management.application;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.management.api.dto.*;
import com.afkir.hotel.management.infrastructure.client.HotelAdminClient;
import com.afkir.hotel.management.infrastructure.client.ReservationAdminClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagementApplicationService {

    private final HotelAdminClient hotelClient;

    private final ReservationAdminClient reservationClient;

    public HotelView createHotel(CreateHotelRequest request) {
        return hotelClient.create(request);
    }

    public List<HotelView> listHotels() {
        return hotelClient.list();
    }

    public RoomTypeView addRoomType(UUID hotelId, CreateRoomTypeRequest request) {
        return hotelClient.addRoomType(hotelId, request);
    }

    public ReservationView getReservation(UUID reservationId) {
        return reservationClient.get(reservationId);
    }

}
