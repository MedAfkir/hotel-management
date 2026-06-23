package com.afkir.hotel.management.infrastructure.client;

import com.afkir.hotel.management.api.dto.ReservationView;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reservation-service", path = "/api/reservations")
public interface ReservationAdminClient {

    @GetMapping("/{id}")
    ReservationView get(@PathVariable("id") UUID id);
}
