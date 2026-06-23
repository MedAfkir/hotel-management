package com.afkir.hotel.management.application;

import com.afkir.hotel.management.api.dto.CreateHotelRequest;
import com.afkir.hotel.management.api.dto.CreateRoomTypeRequest;
import com.afkir.hotel.management.api.dto.HotelView;
import com.afkir.hotel.management.api.dto.ReservationView;
import com.afkir.hotel.management.api.dto.RoomTypeView;
import com.afkir.hotel.management.infrastructure.client.HotelAdminClient;
import com.afkir.hotel.management.infrastructure.client.ReservationAdminClient;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ManagementApplicationService {

    private final HotelAdminClient hotelClient;
    private final ReservationAdminClient reservationClient;

    public ManagementApplicationService(HotelAdminClient hotelClient,
            ReservationAdminClient reservationClient) {
        this.hotelClient = hotelClient;
        this.reservationClient = reservationClient;
    }

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
