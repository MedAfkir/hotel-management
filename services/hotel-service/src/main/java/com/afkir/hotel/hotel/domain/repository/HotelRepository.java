package com.afkir.hotel.hotel.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.hotel.domain.model.Hotel;

public interface HotelRepository {

    Hotel save(Hotel hotel);

    Optional<Hotel> findById(UUID id);

    List<Hotel> findAll();
}
