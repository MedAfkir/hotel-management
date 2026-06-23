package com.afkir.hotel.hotel.domain.repository;

import com.afkir.hotel.hotel.domain.model.Room;
import java.util.List;
import java.util.UUID;

public interface RoomRepository {

    Room save(Room room);

    List<Room> findByHotelId(UUID hotelId);
}
