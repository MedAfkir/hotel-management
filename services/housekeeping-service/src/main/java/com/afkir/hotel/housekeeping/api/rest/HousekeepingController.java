package com.afkir.hotel.housekeeping.api.rest;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import com.afkir.hotel.housekeeping.api.dto.AssignTaskRequest;
import com.afkir.hotel.housekeeping.api.dto.CreateTaskRequest;
import com.afkir.hotel.housekeeping.api.dto.TaskResponse;
import com.afkir.hotel.housekeeping.application.HousekeepingApplicationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/housekeeping")
@RequiredArgsConstructor
public class HousekeepingController {

    private final HousekeepingApplicationService service;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskResponse.from(
                service.createTask(request.hotelId(), request.roomId(), request.type(),
                        request.scheduledDate())));
    }

    @GetMapping("/{id}")
    public TaskResponse get(@PathVariable UUID id) {
        return TaskResponse.from(service.getTask(id));
    }

    @GetMapping
    public List<TaskResponse> byHotel(@RequestParam("hotelId") UUID hotelId) {
        return service.listByHotel(hotelId).stream().map(TaskResponse::from).toList();
    }

    @PostMapping("/{id}/assign")
    public TaskResponse assign(@PathVariable UUID id, @Valid @RequestBody AssignTaskRequest request) {
        return TaskResponse.from(service.assign(id, request.assignee()));
    }

    @PostMapping("/{id}/start")
    public TaskResponse start(@PathVariable UUID id) {
        return TaskResponse.from(service.start(id));
    }

    @PostMapping("/{id}/complete")
    public TaskResponse complete(@PathVariable UUID id) {
        return TaskResponse.from(service.complete(id));
    }

}
