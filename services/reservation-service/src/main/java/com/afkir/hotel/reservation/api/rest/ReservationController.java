package com.afkir.hotel.reservation.api.rest;

import com.afkir.hotel.reservation.api.dto.CreateReservationRequest;
import com.afkir.hotel.reservation.api.dto.ReservationResponse;
import com.afkir.hotel.reservation.api.dto.SetInventoryRequest;
import com.afkir.hotel.reservation.application.ReservationApplicationService;
import com.afkir.hotel.reservation.application.command.CreateReservationCommand;
import com.afkir.hotel.reservation.domain.model.Reservation;
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
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationApplicationService service;

    public ReservationController(ReservationApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(
            @Valid @RequestBody CreateReservationRequest request) {
        Reservation reservation = service.createReservation(new CreateReservationCommand(
                request.idempotencyKey(), request.hotelId(), request.roomTypeId(), request.guestId(),
                request.startDate(), request.endDate(), request.numberOfRooms()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ReservationResponse.from(reservation));
    }

    @PostMapping("/{id}/confirm")
    public ReservationResponse confirm(@PathVariable UUID id) {
        return ReservationResponse.from(service.confirmPayment(id));
    }

    @PostMapping("/{id}/cancel")
    public ReservationResponse cancel(@PathVariable UUID id) {
        return ReservationResponse.from(service.cancel(id));
    }

    @GetMapping("/{id}")
    public ReservationResponse get(@PathVariable UUID id) {
        return ReservationResponse.from(service.getReservation(id));
    }

    @GetMapping
    public List<ReservationResponse> byGuest(@RequestParam("guestId") UUID guestId) {
        return service.findByGuest(guestId).stream().map(ReservationResponse::from).toList();
    }

    @PostMapping("/inventory")
    public ResponseEntity<Void> setInventory(@Valid @RequestBody SetInventoryRequest request) {
        service.setInventory(request.hotelId(), request.roomTypeId(), request.startDate(),
                request.endDate(), request.totalInventory());
        return ResponseEntity.noContent().build();
    }
}
