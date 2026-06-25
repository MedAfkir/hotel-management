package com.afkir.hotel.guest.application;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.guest.domain.model.Guest;
import com.afkir.hotel.guest.domain.repository.GuestRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GuestApplicationService {

    private final GuestRepository repository;

    @Transactional
    public Guest register(String firstName, String lastName, String email, String phone) {
        repository.findByEmail(email).ifPresent(existing -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already registered");
        });
        return repository.save(new Guest(firstName, lastName, email, phone));
    }

    @Transactional(readOnly = true)
    public Guest getGuest(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "guest not found"));
    }

    @Transactional(readOnly = true)
    public Guest getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "guest not found"));
    }

    @Transactional(readOnly = true)
    public List<Guest> listGuests() {
        return repository.findAll();
    }

}
