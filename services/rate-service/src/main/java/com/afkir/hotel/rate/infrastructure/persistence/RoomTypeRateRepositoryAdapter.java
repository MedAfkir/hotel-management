package com.afkir.hotel.rate.infrastructure.persistence;

import com.afkir.hotel.rate.domain.model.RoomTypeRate;
import com.afkir.hotel.rate.domain.model.RoomTypeRateId;
import com.afkir.hotel.rate.domain.repository.RoomTypeRateRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeRateRepositoryAdapter implements RoomTypeRateRepository {

    private final JpaRoomTypeRateRepository jpa;

    public RoomTypeRateRepositoryAdapter(JpaRoomTypeRateRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public RoomTypeRate save(RoomTypeRate rate) {
        return jpa.save(rate);
    }

    @Override
    public Optional<RoomTypeRate> findById(RoomTypeRateId id) {
        return jpa.findById(id);
    }

    @Override
    public List<RoomTypeRate> findRange(UUID hotelId, UUID roomTypeId, LocalDate start,
            LocalDate end) {
        return jpa.findByIdHotelIdAndIdRoomTypeIdAndIdDateBetween(hotelId, roomTypeId, start, end);
    }
}
