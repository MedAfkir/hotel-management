package com.afkir.hotel.housekeeping.infrastructure.persistence;

import com.afkir.hotel.housekeeping.domain.model.HousekeepingTask;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHousekeepingTaskRepository extends JpaRepository<HousekeepingTask, UUID> {

    List<HousekeepingTask> findByHotelId(UUID hotelId);
}
