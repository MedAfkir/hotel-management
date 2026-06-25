package com.afkir.hotel.hotel.api.rest;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import com.afkir.hotel.hotel.api.dto.*;
import com.afkir.hotel.hotel.application.HotelApplicationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelApplicationService service;

    @PostMapping
    public ResponseEntity<HotelResponse> create(@Valid @RequestBody CreateHotelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(HotelResponse.from(
                service.createHotel(request.name(), request.address(), request.city())));
    }

    @GetMapping
    public List<HotelResponse> list() {
        return service.listHotels().stream().map(HotelResponse::from).toList();
    }

    @GetMapping("/{id}")
    public HotelResponse get(@PathVariable UUID id) {
        return HotelResponse.from(service.getHotel(id));
    }

    @PostMapping("/{hotelId}/room-types")
    public ResponseEntity<RoomTypeResponse> addRoomType(@PathVariable UUID hotelId,
                                                        @Valid @RequestBody CreateRoomTypeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(RoomTypeResponse.from(
                service.addRoomType(hotelId, request.name(), request.maxOccupancy())));
    }

    @GetMapping("/{hotelId}/room-types")
    public List<RoomTypeResponse> listRoomTypes(@PathVariable UUID hotelId) {
        return service.listRoomTypes(hotelId).stream().map(RoomTypeResponse::from).toList();
    }

    @PostMapping("/{hotelId}/rooms")
    public ResponseEntity<RoomResponse> addRoom(@PathVariable UUID hotelId,
                                                @Valid @RequestBody CreateRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(RoomResponse.from(
                service.addRoom(hotelId, request.roomTypeId(), request.floor(), request.number(),
                        request.name())));
    }

    @GetMapping("/{hotelId}/rooms")
    public List<RoomResponse> listRooms(@PathVariable UUID hotelId) {
        return service.listRooms(hotelId).stream().map(RoomResponse::from).toList();
    }

}
