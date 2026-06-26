package com.afkir.hotel.reservation.infrastructure.client;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RateClientFallbackFactory implements FallbackFactory<RateClient> {

    @Override
    public RateClient create(Throwable cause) {
        return (hotelId, roomTypeId, date) -> {
            log.warn("rate lookup failed for room type {} on {}: {}", roomTypeId, date,
                    cause.toString());
            return null;
        };
    }

}
