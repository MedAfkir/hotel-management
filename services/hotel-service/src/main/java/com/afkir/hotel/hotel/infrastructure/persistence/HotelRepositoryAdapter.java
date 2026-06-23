package com.afkir.hotel.hotel.infrastructure.persistence;

import com.afkir.hotel.hotel.domain.model.Hotel;
import com.afkir.hotel.hotel.domain.repository.HotelRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class HotelRepositoryAdapter implements HotelRepository {

    private final JpaHotelRepository jpa;

    public HotelRepositoryAdapter(JpaHotelRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Hotel save(Hotel hotel) {
        return jpa.save(hotel);
    }

    @Override
    public Optional<Hotel> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Hotel> findAll() {
        return jpa.findAll();
    }
}
