package com.afkir.hotel.hotel.domain.repository;

import com.afkir.hotel.hotel.domain.model.Hotel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository {

    Hotel save(Hotel hotel);

    Optional<Hotel> findById(UUID id);

    List<Hotel> findAll();
}
