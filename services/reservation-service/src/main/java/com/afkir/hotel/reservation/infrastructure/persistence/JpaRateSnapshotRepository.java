package com.afkir.hotel.reservation.infrastructure.persistence;

import com.afkir.hotel.reservation.domain.model.RateSnapshot;
import com.afkir.hotel.reservation.domain.model.RateSnapshotId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRateSnapshotRepository extends JpaRepository<RateSnapshot, RateSnapshotId> {
}
