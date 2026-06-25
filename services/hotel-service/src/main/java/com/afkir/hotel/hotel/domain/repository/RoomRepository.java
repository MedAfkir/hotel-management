package com.afkir.hotel.hotel.domain.repository;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.hotel.domain.model.Room;

public interface RoomRepository {

    Room save(Room room);

    List<Room> findByHotelId(UUID hotelId);
}
