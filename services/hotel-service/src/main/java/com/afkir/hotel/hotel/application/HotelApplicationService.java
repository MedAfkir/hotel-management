package com.afkir.hotel.hotel.application;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.hotel.domain.model.Hotel;
import com.afkir.hotel.hotel.domain.model.Room;
import com.afkir.hotel.hotel.domain.model.RoomType;
import com.afkir.hotel.hotel.domain.repository.HotelRepository;
import com.afkir.hotel.hotel.domain.repository.RoomRepository;
import com.afkir.hotel.hotel.domain.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class HotelApplicationService {

    private final HotelRepository hotelRepository;

    private final RoomTypeRepository roomTypeRepository;

    private final RoomRepository roomRepository;

    @Transactional
    public Hotel createHotel(String name, String address, String city) {
        return hotelRepository.save(new Hotel(name, address, city));
    }

    @Transactional(readOnly = true)
    public List<Hotel> listHotels() {
        return hotelRepository.findAll();
    }

    @Transactional
    public RoomType addRoomType(UUID hotelId, String name, int maxOccupancy) {
        getHotel(hotelId);
        return roomTypeRepository.save(new RoomType(hotelId, name, maxOccupancy));
    }

    @Transactional(readOnly = true)
    public Hotel getHotel(UUID id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "hotel not found"));
    }

    @Transactional(readOnly = true)
    public List<RoomType> listRoomTypes(UUID hotelId) {
        return roomTypeRepository.findByHotelId(hotelId);
    }

    @Transactional
    public Room addRoom(UUID hotelId, UUID roomTypeId, int floor, String number, String name) {
        getHotel(hotelId);
        return roomRepository.save(new Room(hotelId, roomTypeId, floor, number, name));
    }

    @Transactional(readOnly = true)
    public List<Room> listRooms(UUID hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

}
