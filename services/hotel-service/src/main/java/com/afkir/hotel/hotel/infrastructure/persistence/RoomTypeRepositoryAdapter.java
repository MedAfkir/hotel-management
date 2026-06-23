package com.afkir.hotel.hotel.infrastructure.persistence;

import com.afkir.hotel.hotel.domain.model.RoomType;
import com.afkir.hotel.hotel.domain.repository.RoomTypeRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeRepositoryAdapter implements RoomTypeRepository {

    private final JpaRoomTypeRepository jpa;

    public RoomTypeRepositoryAdapter(JpaRoomTypeRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public RoomType save(RoomType roomType) {
        return jpa.save(roomType);
    }

    @Override
    public Optional<RoomType> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<RoomType> findByHotelId(UUID hotelId) {
        return jpa.findByHotelId(hotelId);
    }
}
