package com.afkir.hotel.guest.api.rest;

import com.afkir.hotel.guest.api.dto.GuestResponse;
import com.afkir.hotel.guest.api.dto.RegisterGuestRequest;
import com.afkir.hotel.guest.application.GuestApplicationService;
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
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestApplicationService service;

    public GuestController(GuestApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GuestResponse> register(@Valid @RequestBody RegisterGuestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GuestResponse.from(
                service.register(request.firstName(), request.lastName(), request.email(),
                        request.phone())));
    }

    @GetMapping("/{id}")
    public GuestResponse get(@PathVariable UUID id) {
        return GuestResponse.from(service.getGuest(id));
    }

    @GetMapping("/search")
    public GuestResponse getByEmail(@RequestParam("email") String email) {
        return GuestResponse.from(service.getByEmail(email));
    }

    @GetMapping
    public List<GuestResponse> list() {
        return service.listGuests().stream().map(GuestResponse::from).toList();
    }
}
