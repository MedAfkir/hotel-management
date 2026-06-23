package com.afkir.hotel.hotel.api.rest;

import com.afkir.hotel.hotel.api.dto.CreateHotelRequest;
import com.afkir.hotel.hotel.api.dto.CreateRoomRequest;
import com.afkir.hotel.hotel.api.dto.CreateRoomTypeRequest;
import com.afkir.hotel.hotel.api.dto.HotelResponse;
import com.afkir.hotel.hotel.api.dto.RoomResponse;
import com.afkir.hotel.hotel.api.dto.RoomTypeResponse;
import com.afkir.hotel.hotel.application.HotelApplicationService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelApplicationService service;

    public HotelController(HotelApplicationService service) {
        this.service = service;
    }

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
