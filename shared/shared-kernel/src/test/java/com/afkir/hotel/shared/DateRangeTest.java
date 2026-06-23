package com.afkir.hotel.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DateRangeTest {

    @Test
    void computesNightsAndDates() {
        DateRange range = new DateRange(LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 4));
        assertEquals(3, range.nights());
        assertEquals(3, range.dates().size());
        assertEquals(LocalDate.of(2026, 7, 1), range.dates().get(0));
    }

    @Test
    void rejectsEndNotAfterStart() {
        assertThrows(DomainException.class,
                () -> new DateRange(LocalDate.of(2026, 7, 4), LocalDate.of(2026, 7, 4)));
    }
}
