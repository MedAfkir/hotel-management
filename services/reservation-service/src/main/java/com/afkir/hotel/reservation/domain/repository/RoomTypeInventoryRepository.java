package com.afkir.hotel.reservation.domain.repository;

import java.util.Optional;

import com.afkir.hotel.reservation.domain.model.RoomTypeInventory;
import com.afkir.hotel.reservation.domain.model.RoomTypeInventoryId;

public interface RoomTypeInventoryRepository {

    RoomTypeInventory save(RoomTypeInventory inventory);

    Optional<RoomTypeInventory> findById(RoomTypeInventoryId id);

}
