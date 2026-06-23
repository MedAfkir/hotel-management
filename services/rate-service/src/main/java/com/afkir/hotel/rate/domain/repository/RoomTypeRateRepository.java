package com.afkir.hotel.rate.domain.repository;

import com.afkir.hotel.rate.domain.model.RoomTypeRate;
import com.afkir.hotel.rate.domain.model.RoomTypeRateId;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomTypeRateRepository {

    RoomTypeRate save(RoomTypeRate rate);

    Optional<RoomTypeRate> findById(RoomTypeRateId id);

    List<RoomTypeRate> findRange(UUID hotelId, UUID roomTypeId, LocalDate start, LocalDate end);
}
