package com.afkir.hotel.housekeeping.application;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.afkir.hotel.housekeeping.domain.model.HousekeepingTask;
import com.afkir.hotel.housekeeping.domain.model.TaskType;
import com.afkir.hotel.housekeeping.domain.repository.HousekeepingTaskRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class HousekeepingApplicationService {

    private final HousekeepingTaskRepository repository;

    @Transactional
    public HousekeepingTask createTask(UUID hotelId, UUID roomId, TaskType type,
                                       LocalDate scheduledDate) {
        return repository.save(new HousekeepingTask(hotelId, roomId, type, scheduledDate));
    }

    @Transactional(readOnly = true)
    public List<HousekeepingTask> listByHotel(UUID hotelId) {
        return repository.findByHotelId(hotelId);
    }

    @Transactional
    public HousekeepingTask assign(UUID id, String assignee) {
        HousekeepingTask task = getTask(id);
        task.assignTo(assignee);
        return repository.save(task);
    }

    @Transactional(readOnly = true)
    public HousekeepingTask getTask(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
    }

    @Transactional
    public HousekeepingTask start(UUID id) {
        HousekeepingTask task = getTask(id);
        task.start();
        return repository.save(task);
    }

    @Transactional
    public HousekeepingTask complete(UUID id) {
        HousekeepingTask task = getTask(id);
        task.complete();
        return repository.save(task);
    }

}
