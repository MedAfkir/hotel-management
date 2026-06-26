package com.afkir.hotel.management.infrastructure.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ReservationAdminClientFallbackFactory implements FallbackFactory<ReservationAdminClient> {

    @Override
    public ReservationAdminClient create(Throwable cause) {
        return id -> {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "reservation-service unavailable", cause);
        };
    }

}
