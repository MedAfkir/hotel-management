package com.afkir.hotel.hotel.infrastructure.persistence;

import com.afkir.hotel.hotel.domain.model.Hotel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHotelRepository extends JpaRepository<Hotel, UUID> {
}
