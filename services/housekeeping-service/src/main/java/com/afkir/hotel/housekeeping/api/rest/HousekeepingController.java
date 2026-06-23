package com.afkir.hotel.housekeeping.api.rest;

import com.afkir.hotel.housekeeping.api.dto.AssignTaskRequest;
import com.afkir.hotel.housekeeping.api.dto.CreateTaskRequest;
import com.afkir.hotel.housekeeping.api.dto.TaskResponse;
import com.afkir.hotel.housekeeping.application.HousekeepingApplicationService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/housekeeping")
public class HousekeepingController {

    private final HousekeepingApplicationService service;

    public HousekeepingController(HousekeepingApplicationService service) {
        this.service = service;
    }

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
