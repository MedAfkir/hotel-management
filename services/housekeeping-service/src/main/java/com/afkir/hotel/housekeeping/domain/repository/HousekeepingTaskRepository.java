package com.afkir.hotel.housekeeping.domain.repository;

import com.afkir.hotel.housekeeping.domain.model.HousekeepingTask;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HousekeepingTaskRepository {

    HousekeepingTask save(HousekeepingTask task);

    Optional<HousekeepingTask> findById(UUID id);

    List<HousekeepingTask> findByHotelId(UUID hotelId);
}
