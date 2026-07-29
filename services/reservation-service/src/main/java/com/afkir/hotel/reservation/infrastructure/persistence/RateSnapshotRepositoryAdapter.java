package com.afkir.hotel.reservation.infrastructure.persistence;

import java.util.Optional;

import com.afkir.hotel.reservation.domain.model.RateSnapshot;
import com.afkir.hotel.reservation.domain.model.RateSnapshotId;
import com.afkir.hotel.reservation.domain.repository.RateSnapshotRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateSnapshotRepositoryAdapter implements RateSnapshotRepository {

    private final JpaRateSnapshotRepository jpa;

    @Override
    public RateSnapshot save(RateSnapshot snapshot) {
        return jpa.save(snapshot);
    }

    @Override
    public Optional<RateSnapshot> findById(RateSnapshotId id) {
        return jpa.findById(id);
    }

}
