package com.afkir.hotel.guest.domain.repository;

import com.afkir.hotel.guest.domain.model.Guest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestRepository {

    Guest save(Guest guest);

    Optional<Guest> findById(UUID id);

    Optional<Guest> findByEmail(String email);

    List<Guest> findAll();
}
