package com.afkir.hotel.rate.infrastructure.persistence;

import com.afkir.hotel.rate.domain.model.RoomTypeRate;
import com.afkir.hotel.rate.domain.model.RoomTypeRateId;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomTypeRateRepository extends JpaRepository<RoomTypeRate, RoomTypeRateId> {

    List<RoomTypeRate> findByIdHotelIdAndIdRoomTypeIdAndIdDateBetween(UUID hotelId, UUID roomTypeId,
            LocalDate start, LocalDate end);
}
