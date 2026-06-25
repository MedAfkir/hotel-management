package com.afkir.hotel.reservation.infrastructure.client;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rate-service", path = "/api/rates")
public interface RateClient {

    @GetMapping
    RateResponse getRate(@RequestParam("hotelId") UUID hotelId,
                         @RequestParam("roomTypeId") UUID roomTypeId,
                         @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

}
