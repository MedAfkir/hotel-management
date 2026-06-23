package com.afkir.hotel.housekeeping.infrastructure.persistence;

import com.afkir.hotel.housekeeping.domain.model.HousekeepingTask;
import com.afkir.hotel.housekeeping.domain.repository.HousekeepingTaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class HousekeepingTaskRepositoryAdapter implements HousekeepingTaskRepository {

    private final JpaHousekeepingTaskRepository jpa;

    public HousekeepingTaskRepositoryAdapter(JpaHousekeepingTaskRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public HousekeepingTask save(HousekeepingTask task) {
        return jpa.save(task);
    }

    @Override
    public Optional<HousekeepingTask> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<HousekeepingTask> findByHotelId(UUID hotelId) {
        return jpa.findByHotelId(hotelId);
    }
}
