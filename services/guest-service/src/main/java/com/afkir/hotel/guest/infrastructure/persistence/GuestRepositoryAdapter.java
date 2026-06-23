package com.afkir.hotel.guest.infrastructure.persistence;

import com.afkir.hotel.guest.domain.model.Guest;
import com.afkir.hotel.guest.domain.repository.GuestRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class GuestRepositoryAdapter implements GuestRepository {

    private final JpaGuestRepository jpa;

    public GuestRepositoryAdapter(JpaGuestRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Guest save(Guest guest) {
        return jpa.save(guest);
    }

    @Override
    public Optional<Guest> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public Optional<Guest> findByEmail(String email) {
        return jpa.findByEmail(email);
    }

    @Override
    public List<Guest> findAll() {
        return jpa.findAll();
    }
}
