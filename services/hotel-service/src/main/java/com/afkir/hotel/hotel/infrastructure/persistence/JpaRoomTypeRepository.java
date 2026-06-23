package com.afkir.hotel.hotel.infrastructure.persistence;

import com.afkir.hotel.hotel.domain.model.RoomType;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomTypeRepository extends JpaRepository<RoomType, UUID> {

    List<RoomType> findByHotelId(UUID hotelId);
}
