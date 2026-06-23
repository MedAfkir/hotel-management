package com.afkir.hotel.rate.api.rest;

import com.afkir.hotel.rate.api.dto.RateResponse;
import com.afkir.hotel.rate.api.dto.UpsertRateRequest;
import com.afkir.hotel.rate.application.RateApplicationService;
import com.afkir.hotel.rate.domain.model.RoomTypeRate;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/rates")
public class RateController {

    private final RateApplicationService service;

    public RateController(RateApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public RateResponse getRate(@RequestParam UUID hotelId, @RequestParam UUID roomTypeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.find(hotelId, roomTypeId, date).map(RateResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "rate not found"));
    }

    @GetMapping("/range")
    public List<RateResponse> getRange(@RequestParam UUID hotelId, @RequestParam UUID roomTypeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.findRange(hotelId, roomTypeId, start, end).stream().map(RateResponse::from)
                .toList();
    }

    @PostMapping
    public ResponseEntity<RateResponse> upsert(@Valid @RequestBody UpsertRateRequest request) {
        RoomTypeRate rate = service.upsert(request.hotelId(), request.roomTypeId(), request.date(),
                request.amount(), request.currency());
        return ResponseEntity.status(HttpStatus.CREATED).body(RateResponse.from(rate));
    }
}
