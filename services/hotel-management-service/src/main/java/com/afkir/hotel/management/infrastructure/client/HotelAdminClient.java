package com.afkir.hotel.management.infrastructure.client;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.management.api.dto.CreateHotelRequest;
import com.afkir.hotel.management.api.dto.CreateRoomTypeRequest;
import com.afkir.hotel.management.api.dto.HotelView;
import com.afkir.hotel.management.api.dto.RoomTypeView;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hotel-service", path = "/api/hotels",
        fallbackFactory = HotelAdminClientFallbackFactory.class)
public interface HotelAdminClient {

    @PostMapping
    HotelView create(@RequestBody CreateHotelRequest request);

    @GetMapping
    List<HotelView> list();

    @PostMapping("/{hotelId}/room-types")
    RoomTypeView addRoomType(@PathVariable("hotelId") UUID hotelId,
                             @RequestBody CreateRoomTypeRequest request);

}
