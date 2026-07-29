package com.afkir.hotel.reservation.application;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.messaging.RateChanged;
import com.afkir.hotel.reservation.domain.model.RateSnapshot;
import com.afkir.hotel.reservation.domain.model.RateSnapshotId;
import com.afkir.hotel.reservation.domain.repository.RateSnapshotRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RateReadModel {

    private final RateSnapshotRepository repository;

    @Transactional
    public void apply(RateChanged event) {
        RateSnapshotId id = new RateSnapshotId(event.hotelId(), event.roomTypeId(), event.date());
        RateSnapshot snapshot = repository.findById(id)
                .orElseGet(() -> new RateSnapshot(event.hotelId(), event.roomTypeId(), event.date(),
                        event.amount(), event.currency()));
        snapshot.update(event.amount(), event.currency());
        repository.save(snapshot);
    }

    @Transactional(readOnly = true)
    public Optional<RateSnapshot> find(UUID hotelId, UUID roomTypeId, LocalDate date) {
        return repository.findById(new RateSnapshotId(hotelId, roomTypeId, date));
    }

}
