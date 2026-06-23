package com.afkir.hotel.hotel.infrastructure.persistence;

import com.afkir.hotel.hotel.domain.model.Room;
import com.afkir.hotel.hotel.domain.repository.RoomRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class RoomRepositoryAdapter implements RoomRepository {

    private final JpaRoomRepository jpa;

    public RoomRepositoryAdapter(JpaRoomRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Room save(Room room) {
        return jpa.save(room);
    }

    @Override
    public List<Room> findByHotelId(UUID hotelId) {
        return jpa.findByHotelId(hotelId);
    }
}
