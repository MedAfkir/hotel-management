package com.afkir.hotel.reservation.infrastructure.persistence;

import com.afkir.hotel.reservation.domain.model.RoomTypeInventory;
import com.afkir.hotel.reservation.domain.model.RoomTypeInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomTypeInventoryRepository
        extends JpaRepository<RoomTypeInventory, RoomTypeInventoryId> {
}
