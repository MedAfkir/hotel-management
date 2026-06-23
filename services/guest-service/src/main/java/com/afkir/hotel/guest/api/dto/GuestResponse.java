package com.afkir.hotel.guest.api.dto;

import com.afkir.hotel.guest.domain.model.Guest;
import java.util.UUID;

public record GuestResponse(UUID id, String firstName, String lastName, String email, String phone,
        String loyaltyTier) {

    public static GuestResponse from(Guest guest) {
        return new GuestResponse(guest.getId(), guest.getFirstName(), guest.getLastName(),
                guest.getEmail(), guest.getPhone(), guest.getLoyaltyTier().name());
    }
}
