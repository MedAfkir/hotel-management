package com.afkir.hotel.reservation.infrastructure.persistence;

import java.util.Optional;

import com.afkir.hotel.reservation.domain.model.RoomTypeInventory;
import com.afkir.hotel.reservation.domain.model.RoomTypeInventoryId;
import com.afkir.hotel.reservation.domain.repository.RoomTypeInventoryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomTypeInventoryRepositoryAdapter implements RoomTypeInventoryRepository {

    private final JpaRoomTypeInventoryRepository jpa;

    @Override
    public RoomTypeInventory save(RoomTypeInventory inventory) {
        return jpa.save(inventory);
    }

    @Override
    public Optional<RoomTypeInventory> findById(RoomTypeInventoryId id) {
        return jpa.findById(id);
    }

}
