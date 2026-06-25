package com.afkir.hotel.reservation.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import com.afkir.hotel.shared.DomainException;

public class InsufficientInventoryException extends DomainException {

    public InsufficientInventoryException(UUID roomTypeId, LocalDate date) {
        super("insufficient inventory for room type " + roomTypeId + " on " + date);
    }

}
