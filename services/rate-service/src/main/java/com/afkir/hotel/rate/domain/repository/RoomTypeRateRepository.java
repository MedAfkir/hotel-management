package com.afkir.hotel.rate.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.rate.domain.model.RoomTypeRate;
import com.afkir.hotel.rate.domain.model.RoomTypeRateId;

public interface RoomTypeRateRepository {

    RoomTypeRate save(RoomTypeRate rate);

    Optional<RoomTypeRate> findById(RoomTypeRateId id);

    List<RoomTypeRate> findRange(UUID hotelId, UUID roomTypeId, LocalDate start, LocalDate end);

}
