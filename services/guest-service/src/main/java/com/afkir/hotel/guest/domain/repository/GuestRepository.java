package com.afkir.hotel.guest.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.guest.domain.model.Guest;


public interface GuestRepository {

    Guest save(Guest guest);

    Optional<Guest> findById(UUID id);

    Optional<Guest> findByEmail(String email);

    List<Guest> findAll();
}
