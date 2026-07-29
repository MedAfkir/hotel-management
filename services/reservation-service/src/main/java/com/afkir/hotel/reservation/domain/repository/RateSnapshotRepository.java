package com.afkir.hotel.reservation.domain.repository;

import java.util.Optional;

import com.afkir.hotel.reservation.domain.model.RateSnapshot;
import com.afkir.hotel.reservation.domain.model.RateSnapshotId;

public interface RateSnapshotRepository {

    RateSnapshot save(RateSnapshot snapshot);

    Optional<RateSnapshot> findById(RateSnapshotId id);

}
