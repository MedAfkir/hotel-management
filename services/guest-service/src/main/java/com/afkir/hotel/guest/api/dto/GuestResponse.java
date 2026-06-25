package com.afkir.hotel.guest.api.dto;

import java.util.UUID;

import com.afkir.hotel.guest.domain.model.Guest;
import lombok.Builder;

@Builder
public record GuestResponse(UUID id, String firstName, String lastName, String email, String phone,
                            String loyaltyTier) {

    public static GuestResponse from(Guest guest) {
        return new GuestResponse(guest.getId(), guest.getFirstName(), guest.getLastName(),
                guest.getEmail(), guest.getPhone(), guest.getLoyaltyTier().name());
    }

}
