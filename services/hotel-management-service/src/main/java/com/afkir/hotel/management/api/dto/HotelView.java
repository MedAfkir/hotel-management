package com.afkir.hotel.management.api.dto;

import java.util.UUID;

public record HotelView(UUID id, String name, String address, String city) {
}
