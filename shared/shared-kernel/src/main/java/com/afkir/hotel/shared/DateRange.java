package com.afkir.hotel.shared;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record DateRange(LocalDate start, LocalDate end) {

    public DateRange {
        Objects.requireNonNull(start, "start must not be null");
        Objects.requireNonNull(end, "end must not be null");
        if (!end.isAfter(start)) {
            throw new DomainException("end must be after start");
        }
    }

    public long nights() {
        return ChronoUnit.DAYS.between(start, end);
    }

    public List<LocalDate> dates() {
        List<LocalDate> result = new ArrayList<>();
        LocalDate cursor = start;
        while (cursor.isBefore(end)) {
            result.add(cursor);
            cursor = cursor.plusDays(1);
        }
        return result;
    }
}
