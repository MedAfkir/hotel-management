package com.afkir.hotel.management.infrastructure.client;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.management.api.dto.CreateHotelRequest;
import com.afkir.hotel.management.api.dto.CreateRoomTypeRequest;
import com.afkir.hotel.management.api.dto.HotelView;
import com.afkir.hotel.management.api.dto.RoomTypeView;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class HotelAdminClientFallbackFactory implements FallbackFactory<HotelAdminClient> {

    @Override
    public HotelAdminClient create(Throwable cause) {
        return new HotelAdminClient() {

            @Override
            public HotelView create(CreateHotelRequest request) {
                throw unavailable(cause);
            }

            @Override
            public List<HotelView> list() {
                log.warn("hotel-service unavailable, returning empty hotel list: {}",
                        cause.toString());
                return List.of();
            }

            @Override
            public RoomTypeView addRoomType(UUID hotelId, CreateRoomTypeRequest request) {
                throw unavailable(cause);
            }
        };
    }

    private ResponseStatusException unavailable(Throwable cause) {
        return new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "hotel-service unavailable",
                cause);
    }

}
