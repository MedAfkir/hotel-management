package com.afkir.hotel.rate.infrastructure.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.rate.domain.model.RoomTypeRate;
import com.afkir.hotel.rate.domain.model.RoomTypeRateId;
import com.afkir.hotel.rate.domain.repository.RoomTypeRateRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomTypeRateRepositoryAdapter implements RoomTypeRateRepository {

    private final JpaRoomTypeRateRepository jpa;

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

    @Override
    public List<RoomTypeRate> findAll() {
        return jpa.findAll();
    }

}
