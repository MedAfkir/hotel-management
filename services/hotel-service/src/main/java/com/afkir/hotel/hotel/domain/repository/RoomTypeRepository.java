package com.afkir.hotel.hotel.domain.repository;

import com.afkir.hotel.hotel.domain.model.RoomType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomTypeRepository {

    RoomType save(RoomType roomType);

    Optional<RoomType> findById(UUID id);

    List<RoomType> findByHotelId(UUID hotelId);
}
