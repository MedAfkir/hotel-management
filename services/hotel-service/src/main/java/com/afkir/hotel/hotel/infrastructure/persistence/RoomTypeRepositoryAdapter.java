package com.afkir.hotel.hotel.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.hotel.domain.model.RoomType;
import com.afkir.hotel.hotel.domain.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomTypeRepositoryAdapter implements RoomTypeRepository {

    private final JpaRoomTypeRepository jpa;

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
