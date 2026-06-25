package com.afkir.hotel.management.api.rest;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import com.afkir.hotel.management.api.dto.*;
import com.afkir.hotel.management.application.ManagementApplicationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin") // TODO config version
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementApplicationService service;

    @PostMapping("/hotels")
    public ResponseEntity<HotelView> createHotel(@Valid @RequestBody CreateHotelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createHotel(request));
    }

    @GetMapping("/hotels")
    public List<HotelView> listHotels() {
        return service.listHotels();
    }

    @PostMapping("/hotels/{hotelId}/room-types")
    public ResponseEntity<RoomTypeView> addRoomType(@PathVariable UUID hotelId,
            @Valid @RequestBody CreateRoomTypeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addRoomType(hotelId, request));
    }

    @GetMapping("/reservations/{id}")
    public ReservationView getReservation(@PathVariable UUID id) {
        return service.getReservation(id);
    }

}
