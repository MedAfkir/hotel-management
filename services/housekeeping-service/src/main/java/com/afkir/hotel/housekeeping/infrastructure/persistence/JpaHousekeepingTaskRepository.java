package com.afkir.hotel.housekeeping.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.housekeeping.domain.model.HousekeepingTask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHousekeepingTaskRepository extends JpaRepository<HousekeepingTask, UUID> {

    List<HousekeepingTask> findByHotelId(UUID hotelId);

}
