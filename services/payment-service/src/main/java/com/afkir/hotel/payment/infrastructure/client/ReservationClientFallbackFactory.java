package com.afkir.hotel.payment.infrastructure.client;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReservationClientFallbackFactory implements FallbackFactory<ReservationClient> {

    @Override
    public ReservationClient create(Throwable cause) {
        return id -> log.warn("could not confirm reservation {}: {}", id, cause.toString());
    }

}
