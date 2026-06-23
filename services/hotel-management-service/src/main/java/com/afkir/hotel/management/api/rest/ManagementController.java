package com.afkir.hotel.management.api.rest;

import com.afkir.hotel.management.api.dto.CreateHotelRequest;
import com.afkir.hotel.management.api.dto.CreateRoomTypeRequest;
import com.afkir.hotel.management.api.dto.HotelView;
import com.afkir.hotel.management.api.dto.ReservationView;
import com.afkir.hotel.management.api.dto.RoomTypeView;
import com.afkir.hotel.management.application.ManagementApplicationService;
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
@RequestMapping("/api/admin")
public class ManagementController {

    private final ManagementApplicationService service;

    public ManagementController(ManagementApplicationService service) {
        this.service = service;
    }

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
