package com.afkir.hotel.rate.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.afkir.hotel.rate.domain.model.RoomTypeRate;
import lombok.Builder;

@Builder
public record RateResponse(LocalDate date, BigDecimal amount, String currency) {

    public static RateResponse from(RoomTypeRate rate) {
        return new RateResponse(rate.getId().getDate(), rate.getAmount(), rate.getCurrency());
    }

}
