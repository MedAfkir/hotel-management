package com.afkir.hotel.reservation.domain.repository;

import com.afkir.hotel.reservation.domain.model.RoomTypeInventory;
import com.afkir.hotel.reservation.domain.model.RoomTypeInventoryId;
import java.util.Optional;

public interface RoomTypeInventoryRepository {

    RoomTypeInventory save(RoomTypeInventory inventory);

    Optional<RoomTypeInventory> findById(RoomTypeInventoryId id);
}
