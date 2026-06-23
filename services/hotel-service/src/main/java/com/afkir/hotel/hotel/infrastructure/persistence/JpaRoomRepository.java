package com.afkir.hotel.hotel.infrastructure.persistence;

import com.afkir.hotel.hotel.domain.model.Room;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomRepository extends JpaRepository<Room, UUID> {

    List<Room> findByHotelId(UUID hotelId);
}
