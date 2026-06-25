package com.afkir.hotel.hotel.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.hotel.domain.model.RoomType;

public interface RoomTypeRepository {

    RoomType save(RoomType roomType);

    Optional<RoomType> findById(UUID id);

    List<RoomType> findByHotelId(UUID hotelId);
}
