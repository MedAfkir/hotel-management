package com.afkir.hotel.payment.infrastructure.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "reservation-service", path = "/api/reservations")
public interface ReservationClient {

    @PostMapping("/{id}/confirm")
    void confirm(@PathVariable UUID id);

}
